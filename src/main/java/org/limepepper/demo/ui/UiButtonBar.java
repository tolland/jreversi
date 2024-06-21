package org.limepepper.demo.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.limepepper.demo.command.CommandManager;
import org.limepepper.demo.event.GameEvent;
import org.limepepper.demo.model.Tile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * the one with the buttons
 */
public class UiButtonBar extends ToolBar {

    private static final Logger logger = LoggerFactory.getLogger(UiButtonBar.class);

    private UiButtonBar() {
        super();

        MenuButton lightPlayerMenuButton = new MenuButton("White: Human");
        MenuButton darkPlayerMenuButton = new MenuButton("Black: Computer");
        var handler = new UiButtonBarHandler(lightPlayerMenuButton,                darkPlayerMenuButton        );

        Button btnDumpBoard = new Button("Dump");
        btnDumpBoard.setTooltip(new Tooltip("Serialize the board to console"));

        btnDumpBoard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println(game.board.toString());
                System.out.println("dumping");
                //PrintWriter printWriter = new PrintWriter(System.out,true);
                fireEvent(GameEvent.createDump());
            }
        });

        add(btnDumpBoard);

        Button btnUndo = new Button("Undo");
        btnUndo.setDisable(true);
        btnUndo.setTooltip(new Tooltip("Unmake that move"));
        btnUndo.setOnAction(event -> {
            logger.debug("handling action on button - undoing");
            fireEvent(GameEvent.createUndo());
        });

        CommandManager.getInstance().addListener((undoStack, redoStack) -> {
            if(undoStack.size() > 0) {
                btnUndo.setDisable(false);
            } else {
                btnUndo.setDisable(true);
            }
            logger.debug("handling disabling undo button");
        });

        add(btnUndo);

        Button btnRedo = new Button("Redo");
        btnRedo.setDisable(true);
        btnRedo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("redoing here");

                fireEvent(GameEvent.createRedo());
            }
        });

        CommandManager.getInstance().addListener((undoStack, redoStack) -> {
            if(redoStack.size() > 0) {
                btnRedo.setDisable(false);
            } else {
                btnRedo.setDisable(true);
            }
            logger.debug("considering whether ti disable redo");
        });


        add(btnRedo);

        Button btnRestart = new Button("Restart");

        Tooltip tooltip = new Tooltip("Resets the game and remove history");
        Image image = new Image(
                getClass().getResourceAsStream("/images/warn.png")
        );
        tooltip.setGraphic(new ImageView(image));

        btnRestart.setTooltip(tooltip);



        btnRestart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("restarting");
                //PrintWriter printWriter = new PrintWriter(System.out,true);
                fireEvent(GameEvent.createRestart());

            }
        });
        add(btnRestart);

        //Label lightPlayerType = new Label("White: Human");

        MenuItem lightPlayerMenuItem1 = new MenuItem("Human");
        lightPlayerMenuItem1.setId("LightPlayerHuman");
        lightPlayerMenuItem1.setOnAction(handler);
        MenuItem lightPlayerMenuItem2 = new MenuItem("Computer");
        lightPlayerMenuItem2.setId("LightPlayerComputer");
        lightPlayerMenuItem2.setOnAction(handler);
        lightPlayerMenuButton.getItems().addAll(lightPlayerMenuItem1, lightPlayerMenuItem2);
        lightPlayerMenuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("menuButton = " + lightPlayerMenuButton);
            }
        });

        //lightPlayerType.setLabelFor(lightPlayerMenuButton);
        //lightPlayerType.setMnemonicParsing(true);

        //getButtons().add(lightPlayerType);
        add(lightPlayerMenuButton);

      //  Label darkPlayerType = new Label("Black: Computer");
        MenuItem darkPlayerMenuItem1 = new MenuItem("Human");
        darkPlayerMenuItem1.setId("DarkPlayerHuman");
        darkPlayerMenuItem1.setOnAction(handler);
        MenuItem darkPlayerMenuItem2 = new MenuItem("Computer");
        darkPlayerMenuItem2.setId("DarkPlayerComputer");
        darkPlayerMenuItem2.setOnAction(handler);

        darkPlayerMenuButton.getItems().addAll(darkPlayerMenuItem1, darkPlayerMenuItem2);

  //      darkPlayerType.setLabelFor(darkPlayerMenuButton);
   //     darkPlayerType.setMnemonicParsing(true);

      //  getButtons().add(darkPlayerType);
        add(darkPlayerMenuButton);

        ComboBox lightPlayerType = UiPlayerTypeSelector.create(Tile.LIGHT);

        add(lightPlayerType);




    }

    public static UiButtonBar create() {
        return new UiButtonBar();
    }

    public void add(Node node) {
        getItems().add(node);
    }
}
