package org.limepepper.demo.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import org.limepepper.demo.event.GameEvent;
import javafx.scene.control.Menu;

public class MainMenuHandler  implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Main Menu Clicked"+event.getSource());
        if (event.getSource() instanceof MenuItem menuItem) {
            System.out.println("Menu Item Clicked: " + menuItem.getText());
            System.out.println(menuItem.getId());
            switch(menuItem.getId()) {
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
