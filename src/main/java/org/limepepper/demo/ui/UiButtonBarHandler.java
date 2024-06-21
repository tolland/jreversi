package org.limepepper.demo.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class UiButtonBarHandler implements EventHandler<ActionEvent> {
    private final MenuButton lightPlayerMenuButton;
    private final MenuButton darkPlayerMenuButton;

    public UiButtonBarHandler(MenuButton lightPlayerMenuButton, MenuButton darkPlayerMenuButton) {
        this.lightPlayerMenuButton = lightPlayerMenuButton;
        this.darkPlayerMenuButton = darkPlayerMenuButton;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Main Menu Clicked"+event.getSource());
        Object source = event.getSource();
        if (source instanceof MenuItem menuItem) {
            System.out.println("Menu Item Clicked: " + menuItem.getText());
            System.out.println(menuItem.getId());
            switch(menuItem.getId()) {
                case "LightPlayerHuman":
                    //menuItem.get.setText(menuItem.getText());
                    lightPlayerMenuButton.setText("White: Human");
                    break;
                case "LightPlayerComputer":
                    lightPlayerMenuButton.setText("White: Computer");
                    break;
                case "DarkPlayerHuman":
                    //menuItem.get.setText(menuItem.getText());
                    darkPlayerMenuButton.setText("Black: Human");
                    break;
                case "DarkPlayerComputer":
                    darkPlayerMenuButton.setText("Black: Computer");
                    break;
            }
        }

    }
}
