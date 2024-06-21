package org.limepepper.demo.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import org.limepepper.demo.command.Command;
import org.limepepper.demo.command.CommandManager;
import org.limepepper.demo.command.UndoListener;
import org.limepepper.demo.event.GameEvent;

import java.util.Stack;

/**
 * the one with the buttons
 */
public class UiButtonBar extends ButtonBar {

    private UiButtonBar() {
        super();

        MenuButton lightPlayerMenuButton = new MenuButton("White: Human");
        MenuButton darkPlayerMenuButton = new MenuButton("Black: Computer");
        var handler = new UiButtonBarHandler(lightPlayerMenuButton,                darkPlayerMenuButton        );

        Button btnDumpBoard = new Button("Dump");

        btnDumpBoard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println(game.board.toString());
                System.out.println("dumping");
                //PrintWriter printWriter = new PrintWriter(System.out,true);
                fireEvent(GameEvent.createDump());
            }
        });

        addButton(btnDumpBoard);

        Button btnUndo = new Button("Undo");
        btnUndo.setDisable(true);
        btnUndo.setOnAction(event -> {
            System.out.println("undoing");
            fireEvent(GameEvent.createUndo());
        });

        CommandManager.getInstance().addListener((undoStack, redoStack) -> {
            if(undoStack.size() > 0) {
                btnUndo.setDisable(false);
            } else {
                btnUndo.setDisable(true);
            }
            System.out.println("doing stuff here");
        });

        addButton(btnUndo);

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
            System.out.println("doing stuff here in redo");
        });


        addButton(btnRedo);

        Button btnRestart = new Button("Restart");

        btnRestart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("restarting");
                //PrintWriter printWriter = new PrintWriter(System.out,true);
                fireEvent(GameEvent.createRestart());

            }
        });
        addButton(btnRestart);

        Label lightPlayerType = new Label("White: Human");

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

        lightPlayerType.setLabelFor(lightPlayerMenuButton);
        lightPlayerType.setMnemonicParsing(true);

        getButtons().add(lightPlayerType);
        getButtons().add(lightPlayerMenuButton);

        Label darkPlayerType = new Label("Black: Computer");
        MenuItem darkPlayerMenuItem1 = new MenuItem("Human");
        darkPlayerMenuItem1.setId("DarkPlayerHuman");
        darkPlayerMenuItem1.setOnAction(handler);
        MenuItem darkPlayerMenuItem2 = new MenuItem("Computer");
        darkPlayerMenuItem2.setId("DarkPlayerComputer");
        darkPlayerMenuItem2.setOnAction(handler);

        darkPlayerMenuButton.getItems().addAll(darkPlayerMenuItem1, darkPlayerMenuItem2);

        darkPlayerType.setLabelFor(darkPlayerMenuButton);
        darkPlayerType.setMnemonicParsing(true);

        getButtons().add(darkPlayerType);
        getButtons().add(darkPlayerMenuButton);


    }

    public static UiButtonBar create() {
        return new UiButtonBar();
    }

    public void addButton(Button button) {
        getButtons().add(button);
    }
}
