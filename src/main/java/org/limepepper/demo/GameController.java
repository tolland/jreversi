package org.limepepper.demo;

import com.almasb.fxgl.minigames.lockpicking.LockPickView;
import javafx.application.HostServices;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.limepepper.demo.ai.MinimaxService;
import org.limepepper.demo.command.CommandManager;
import org.limepepper.demo.command.MakeMove;
import org.limepepper.demo.event.GameEvent;
import org.limepepper.demo.helper.BoardHelper;
import org.limepepper.demo.model.Game;
import org.limepepper.demo.model.Move;
import org.limepepper.demo.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


/**
 * this is a POJO controller class
 */
public class GameController {
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    Game game;
    Stage stage;
    private MinimaxService backgroundTask;

    private GameGridPane gridPane;
    BorderPane root;
    private final CommandManager commandManager = CommandManager.getInstance();

    FileChooser fileChooser = new FileChooser();

    HostServices hostServices;

    public GameController(Stage stage) {
        this.stage = stage;
        hostServices = (HostServices) stage.getProperties().get("hostServices");
    }

    public void startNewGame() {
        game = new Game();
        gridPane = GameGridPane.create(game);
        renderBoard1();
        gridPane.renderBoard();
        gridPane.updateBoard();
        backgroundTask = MinimaxService.create(stage, game);
        game.start();
    }

    public void renderBoard1() {

        stage.setWidth(800);
        stage.setHeight(750);

        // create the root of the window
        root = new BorderPane();

        // regular menu (File, Edit, Help)
        MenuBar menubar = UiMainMenu.create();
        menubar.prefWidthProperty().bind(stage.widthProperty());

        // game buttons (Dump, Undo, Restart)
        UiButtonBar buttonBar = UiButtonBar.create();

        VBox topBox = new VBox();
        topBox.getChildren().addAll(menubar, buttonBar);


        root.setTop(topBox);

        UiNotificationArea notificationArea = UiNotificationArea.create();

        HBox content = new HBox();
        content.getChildren().addAll(gridPane, notificationArea);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(content);

        root.setCenter(vbox);


        UiStatusBar statusBar = UiStatusBar.create();

        root.setBottom(statusBar);

        stage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case F1:
                    System.out.println("YAYY GOT IT HERE");
                    hostServices.showDocument("http://stackoverflow.com/");
                    break;
            }
        });

        stage.addEventFilter(
                GameEvent.MOVE,
                event -> {
                    System.out.println("handling move in event filter - in controller");
                    //handleMove(event.getMove().getX(),event.getMove().getY());
                    int x = event.getMove().getX();
                    int y = event.getMove().getY();
                    if (BoardHelper.isValidMove(game.board, x, y)) {
                        commandManager.executeCommand(new MakeMove(game, x, y));
                        gridPane.updateBoard();
                        notificationArea.appendText(event.getMove().toString() + "\n");
                        root.fireEvent(GameEvent.createMoved(new Move(x, y)));
                    } else {
                        System.out.println("Invalid move");
                    }
                }
        );

        stage.addEventFilter(
                GameEvent.UNDO,
                event -> {
                    logger.debug("addEventFilter handling undoing");
                    commandManager.undo();
                    notificationArea.appendText(event.getMessage() + "\n");
                    gridPane.updateBoard();
                }
        );

//        stage.addEventFilter(
//                GameEvent.UNDO,
//                event -> {
//                    logger.trace("addEventFilter handling undoing");
//                    commandManager.undo();
//                    notificationArea.appendText(event.getMessage() + "\n");
//                    gridPane.updateBoard();
//                }
//        );

        stage.addEventFilter(
                GameEvent.REDO,
                event -> {
                    logger.trace("handling redoing");
                    commandManager.redo();
                    notificationArea.appendText(event.getMessage() + "\n");
                    gridPane.updateBoard();
                }
        );
        stage.addEventFilter(
                GameEvent.RESTART,
                event -> {
                    logger.trace("handling restarting");
                    notificationArea.appendText(event.getMessage() + "\n");
                    game.restart();
                    commandManager.reset();
                    gridPane.updateBoard();
                }
        );

        stage.addEventFilter(
                GameEvent.WAITING,
                event -> {
                    System.out.println("handling WAITING in event filter - in controller");
                    //handleMove(event.getMove().getX(),event.getMove().getY());
                    notificationArea.appendText("deciding what to do next \n");

                }
        );

        stage.addEventFilter(
                GameEvent.MOVED,
                event -> {
                    System.out.println("handling MOVED in event filter - in controller");
                    //handleMove(event.getMove().getX(),event.getMove().getY());
                    notificationArea.appendText("deciding what to do next \n");
                }
        );

        stage.addEventFilter(
                GameEvent.DUMP,
                event -> {
                    notificationArea.appendText(event.getMessage() + "\n");
                    logger.info(game.board.toString());
                }
        );

        stage.addEventFilter(
                GameEvent.UPDATED,
                event -> {
                    notificationArea.appendText(event.getMessage() + "\n");
                    //System.out.println(game.board.toString());
                }
        );

        Scene scene = new Scene(root, 650, 650, Color.ALICEBLUE);

        stage.setTitle("Hello!");

        scene.getStylesheets().add(getClass().getResource("/style/menu.css").toExternalForm());


        stage.setScene(scene);

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.jpg")
                , new FileChooser.ExtensionFilter("HTML Files", "*.png")
        );

        GameApplication.eventBus.addEventHandler(GameEvent.FILE_OPEN, event -> {
            System.out.println("handling FILE_OPEN in event handler - in controller");
            //handleMove(event.getMove().getX(),event.getMove().getY());
            File selectedFile = fileChooser.showOpenDialog(stage);

            notificationArea.appendText("XXX\n");
        });

        stage.show();
    }


}
