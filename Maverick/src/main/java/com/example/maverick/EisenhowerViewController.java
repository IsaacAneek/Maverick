package com.example.maverick;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class EisenhowerViewController {

    public void onAddImportantUrgentClicked(ActionEvent actionEvent) {
        TextField txt = new TextField();
        txt.setAlignment(Pos.CENTER);
        VBox parentVbox = (VBox) ((Button) actionEvent.getSource()).getParent();
        VBox taskList = (VBox) parentVbox.getChildren().get(1);
        taskList.getChildren().add(txt);
    }

    public void onNotImportantUrgentClicked(ActionEvent actionEvent) {
        TextField txt = new TextField();
        txt.setAlignment(Pos.CENTER);
        VBox parentVbox = (VBox) ((Button) actionEvent.getSource()).getParent();
        VBox taskList = (VBox) parentVbox.getChildren().get(0);
        taskList.getChildren().add(txt);
    }

    public void onAddImportantNotUrgentClicked(ActionEvent actionEvent) {
        TextField txt = new TextField();
        txt.setAlignment(Pos.CENTER);
        VBox parentVbox = (VBox) ((Button) actionEvent.getSource()).getParent();
        VBox taskList = (VBox) parentVbox.getChildren().get(1);
        taskList.getChildren().add(txt);
    }

    public void onNotImportantNotUrgentClicked(ActionEvent actionEvent) {
        TextField txt = new TextField();
        txt.setAlignment(Pos.CENTER);
        VBox parentVbox = (VBox) ((Button) actionEvent.getSource()).getParent();
        VBox taskList = (VBox) parentVbox.getChildren().get(0);
        taskList.getChildren().add(txt);
    }
}
