package org.limepepper.demo.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.stage.FileChooser;
import org.limepepper.demo.GameApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.limepepper.demo.event.GameEvent;

public class UiMainMenuHandler implements EventHandler<ActionEvent> {

    private static final Logger logger = LoggerFactory.getLogger(UiMainMenuHandler.class);

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Main Menu Clicked"+event.getSource());
        if (event.getSource() instanceof MenuItem menuItem) {
            System.out.println("Menu Item Clicked: " + menuItem.getText());
            System.out.println(menuItem.getId());
            switch(menuItem.getId()) {
                case "file-open":
                    logger.debug("open file - in menu click handler");
                    FileChooser fileChooser = new FileChooser();
//                    File selectedFile = fileChooser.showOpenDialog(stage);
                    //fireEvent(GameEvent.createLoadGame(selectedFile));
                    //GameApplication.eventBus.fireEvent(GameEvent.fileOpen());
                    break;
                case "file-exit":
                    System.exit(0);
                    break;
                case "edit-undo":
                    System.out.println("undoing");
                    Menu menu = menuItem.getParentMenu();


                    //menuItem.fireEvent(GameEvent.createUndo());
                    //menufireEvent(GameEvent.createUndo());
                    break;
            }
        }
    }
}
