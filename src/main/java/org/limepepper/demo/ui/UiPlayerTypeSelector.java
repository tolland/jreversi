package org.limepepper.demo.ui;

import javafx.scene.control.ComboBox;
import org.limepepper.demo.model.Tile;

public class UiPlayerTypeSelector extends ComboBox<String> {

    private UiPlayerTypeSelector() {
        super();
        getItems().addAll("Human", "Computer");
        setValue("Human");

        setOnAction((event) -> {
            int selectedIndex = getSelectionModel().getSelectedIndex();
            Object selectedItem = getSelectionModel().getSelectedItem();

            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            System.out.println("   ComboBox.getValue(): " + getValue());
        });
    }

    public static UiPlayerTypeSelector create(Tile player
    ) {
        return new UiPlayerTypeSelector();
    }

    public boolean isHuman() {
        return getValue().equals("Human");
    }
}
