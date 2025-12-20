package com.example.maverick;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class CardViewController {
    public void onDeleteButtonClicked(ActionEvent actionEvent) {
        HBox hbox = (HBox) ((Button) actionEvent.getSource()).getParent().getParent();
        VBox hboxContainer = (VBox) hbox.getParent();
        hboxContainer.getChildren().remove(hbox);
    }
}
