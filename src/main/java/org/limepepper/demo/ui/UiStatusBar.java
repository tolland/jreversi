package org.limepepper.demo.ui;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import org.controlsfx.control.StatusBar;

public class UiStatusBar extends StatusBar {


    private UiStatusBar() {
        getLeftItems().add(new Button("Info"));
        setProgress(.5);



        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll("Easy", "Medium", "Hard");

        getLeftItems().add(comboBox);
    }

    public static UiStatusBar create() {
        return new UiStatusBar();
    }
}
