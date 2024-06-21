package org.limepepper.demo.ui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import org.limepepper.demo.command.CommandManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        fileMenu = new Menu("File");

        MenuItem filemenu1 = new MenuItem("New");
        filemenu1.setOnAction(handler);

        MenuItem filemenuOpen = new MenuItem("Open");
        filemenuOpen.setId("file-open");
        filemenuOpen.setOnAction(handler);
        MenuItem filemenu2 = new MenuItem("Save");
        filemenu2.setOnAction(handler);
        MenuItem filemenu3 = new MenuItem("Exit");
        filemenu3.setId("file-exit");
        filemenu3.setOnAction(handler);
        fileMenu.getItems().addAll(filemenu1, filemenuOpen, filemenu2, filemenu3);

        editMenu = new Menu("Edit");
        MenuItem editMenuUndo = new MenuItem("Undo");
        editMenuUndo.setId("edit-undo");
        editMenuUndo.setOnAction(handler);
        editMenuUndo.setDisable(true);

        MenuItem editMenuRedo = new MenuItem("Redo");
        editMenuRedo.setOnAction(handler);
        editMenuRedo.setId("edit-redo");
        editMenuRedo.setDisable(true);
        CommandManager.getInstance().addListener((undoStack, redoStack) -> {
            if(undoStack.size() > 0) {
                editMenuUndo.setDisable(false);
            }else{
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

        helpMenu = new Menu("Help");
        MenuItem HelpMenu1 = new MenuItem("Get Help");
        MenuItem HelpMenu2 = new MenuItem("Licenses");
        MenuItem HelpMenu3 = new MenuItem("About Reversi");
        HelpMenu3.setOnAction(handler);
        helpMenu.getItems().addAll(HelpMenu1, HelpMenu2, new SeparatorMenuItem(), HelpMenu3);


        getMenus().addAll(fileMenu, editMenu, helpMenu);
    }

    public static UiMainMenu create() {
        return new UiMainMenu();
    }
}
