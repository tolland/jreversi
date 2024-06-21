package org.limepepper.demo.ui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import org.limepepper.demo.GameApplication;
import org.limepepper.demo.command.CommandManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class UiMainMenu extends MenuBar {

    private static final Logger logger = LoggerFactory.getLogger(UiMainMenu.class);

    final Menu fileMenu;
    final Menu editMenu;
    final Menu helpMenu;

    private UiMainMenu() {
        super();


        //setUseSystemMenuBar(false);
        setAccessibleHelp("Main Menu");


        var handler = new UiMainMenuHandler();

        fileMenu = new Menu("_File");


        //fileMenu.setStyle("-fx-background-color: #f0f0f0;");


        logger.debug("setStyle getStyleClass() = " + fileMenu.getStyleClass());


        MenuItem newGame = new MenuItem("_New");
        newGame.setOnAction(handler);

        MenuItem filemenuOpen = new MenuItem("_Open File...");
        filemenuOpen.setId("file-open");
        filemenuOpen.setOnAction(handler);
        filemenuOpen.setAccelerator(KeyCombination.keyCombination("shortcut+O"));

        filemenuOpen.setGraphic(new ImageView(new Image(GameApplication.class.getResource("/images/open-folder.png").toExternalForm())));

        MenuItem fileMenuSep1 = new SeparatorMenuItem();

        MenuItem save = new MenuItem("_Save");
        save.setOnAction(handler);


        save.setGraphic(new ImageView(new Image(GameApplication.class.getResource("/images/save.png").toExternalForm())));


        MenuItem fileMenuSep2 = new SeparatorMenuItem();
        MenuItem exit = new MenuItem("E_xit");
        exit.setAccelerator(KeyCombination.keyCombination("shortcut+Q"));
        exit.setId("file-exit");
        exit.setOnAction(handler);
        fileMenu.getItems().addAll(newGame, filemenuOpen, fileMenuSep1, save, fileMenuSep2, exit);

        editMenu = new Menu("_Edit");
        MenuItem editMenuUndo = new MenuItem("Undo");
        editMenuUndo.setId("edit-undo");
        editMenuUndo.setOnAction(handler);
        editMenuUndo.setDisable(true);

        editMenuUndo.setGraphic(new ImageView(new Image(GameApplication.class.getResource("/images/icons8-undo-16.png").toExternalForm())));

        MenuItem editMenuRedo = new MenuItem("Redo");
        editMenuRedo.setOnAction(handler);
        editMenuRedo.setId("edit-redo");
        editMenuRedo.setDisable(true);

        editMenuRedo.setGraphic(new ImageView(new Image(GameApplication.class.getResource("/images/icons8-redo-16.png").toExternalForm())));

        CommandManager.getInstance().addListener((undoStack, redoStack) -> {
            if (undoStack.size() > 0) {
                editMenuUndo.setDisable(false);
            } else {
                editMenuUndo.setDisable(true);
            }
            if (redoStack.size() > 0) {
                editMenuRedo.setDisable(false);
            } else {
                editMenuRedo.setDisable(true);
            }
            logger.trace("Handling undo/redo menu items");
        });

        MenuItem editMenuSep = new SeparatorMenuItem();
        MenuItem editMenu1 = new MenuItem("Cut");
        MenuItem editMenu2 = new MenuItem("Copy");
        MenuItem editMenu3 = new MenuItem("Paste");
        editMenu.getItems().addAll(editMenuUndo, editMenuRedo, editMenuSep, editMenu1, editMenu2, editMenu3);

        helpMenu = new Menu("_Help");
        MenuItem HelpMenu1 = new MenuItem("Get _Help");
        MenuItem HelpMenu2 = new MenuItem("_Licenses");


        HelpMenu2.setGraphic(new ImageView(new Image(GameApplication.class.getResource("/images/certificate.png").toExternalForm())));


        MenuItem HelpMenu3 = new MenuItem("_About Reversi");
        HelpMenu3.setOnAction(handler);
        helpMenu.getItems().addAll(HelpMenu1, HelpMenu2, new SeparatorMenuItem(), HelpMenu3);


        getMenus().addAll(fileMenu, editMenu, helpMenu);
    }

    public static UiMainMenu create() {
        return new UiMainMenu();
    }
}
