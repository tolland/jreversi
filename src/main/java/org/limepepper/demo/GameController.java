package org.limepepper.demo;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.limepepper.demo.ai.MinimaxService;
import org.limepepper.demo.command.CommandManager;
import org.limepepper.demo.command.MakeMove;
import org.limepepper.demo.event.GameEvent;
import org.limepepper.demo.helper.BoardHelper;
import org.limepepper.demo.model.Game;
import org.limepepper.demo.model.Move;
import org.limepepper.demo.ui.*;


/**
 * this is a POJO controller class

 */
public class GameController {

    Game game;
    Stage stage;
    private MinimaxService backgroundTask;

    private GameGridPane gridPane;
    BorderPane root;
    private final CommandManager commandManager = CommandManager.getInstance();

    public GameController(Stage stage) {
        this.stage = stage;
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
        stage.setHeight(645);



        // create the root of the window
        root = new BorderPane();

        // regular menu (File, Edit, Help)
        MenuBar menubar = MainMenu.create();
        menubar.prefWidthProperty().bind(stage.widthProperty());

        // game buttons (Dump, Undo, Restart)
        UiButtonBar buttonBar = UiButtonBar.create();

        VBox topBox = new VBox();
        topBox.getChildren().addAll(menubar,buttonBar);


        root.setTop(topBox);
        
        NotificationArea notificationArea = NotificationArea.create();

        HBox content = new HBox();
        content.getChildren().addAll(gridPane, notificationArea);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(content);

        root.setCenter(vbox);


        UiStatusBar statusBar = UiStatusBar.create();

        root.setBottom(statusBar);

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
                    }else {
                        System.out.println("Invalid move");
                    }
                }
        );

        stage.addEventFilter(
                GameEvent.UNDO,
                event -> {
                    System.out.println("handling undoing");
                    commandManager.undo();
                    notificationArea.appendText(event.getMessage() + "\n");
                    gridPane.updateBoard()  ;
                }
        );

        stage.addEventFilter(
                GameEvent.REDO,
                event -> {
                    System.out.println("handling redoing");
                    commandManager.redo();
                    notificationArea.appendText(event.getMessage() + "\n");
                    gridPane.updateBoard()  ;
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
                    System.out.println(game.board.toString());
                }
        );
        stage.addEventFilter(
                GameEvent.RESTART,
                event -> {
                    System.out.println("handling restarting");
                    notificationArea.appendText(event.getMessage() + "\n");
                    game.restart();
                    gridPane.updateBoard();
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
        stage.setScene(scene);
        stage.show();
    }


}
