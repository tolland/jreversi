package org.limepepper.demo.ui;

import javafx.scene.control.TextArea;

public class NotificationArea extends TextArea {

    private NotificationArea() {
        super();
        setEditable(false);
        setWrapText(true);

        setText("New Text\n");

        for (int i = 0; i < 10; i++) {
            appendText("some message\n");
        }

    }

    public static NotificationArea create() {
        return new NotificationArea();
    }
}
