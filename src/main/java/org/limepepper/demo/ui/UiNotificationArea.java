package org.limepepper.demo.ui;

import javafx.scene.control.TextArea;

public class UiNotificationArea extends TextArea {

    private UiNotificationArea() {
        super();
        setEditable(false);
        setWrapText(true);

        setText("Game Messages\n");

//        for (int i = 0; i < 10; i++) {
//            appendText("some message\n");
//        }

    }

    public static UiNotificationArea create() {
        return new UiNotificationArea();
    }
}
