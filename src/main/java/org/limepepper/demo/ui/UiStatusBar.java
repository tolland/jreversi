package org.limepepper.demo.ui;

import javafx.scene.control.Button;
import org.controlsfx.control.StatusBar;

public class UiStatusBar extends StatusBar {


    private UiStatusBar() {
        getLeftItems().add(new Button("Info"));
        setProgress(.5);
    }

    public static UiStatusBar create() {
        return new UiStatusBar();
    }
}
