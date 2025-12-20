package com.example.maverick;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class EisenhowerViewController {
    @FXML private VBox importantUrgentVBox;
    @FXML private VBox importantNotUrgentVBox;
    @FXML private VBox notImportantUrgentVBox;
    @FXML private VBox notImportantNotUrgentVBox;

    //private EisenhowerMatrix eisenhowerMatrix = new EisenhowerMatrix();

    public EisenhowerMatrix getEisenhowerMatrix() {
        EisenhowerMatrix eisenhowerMatrix = new EisenhowerMatrix();
        importantNotUrgentVBox.getChildren().forEach(node -> {
            TextField txtField = (TextField) node;
            eisenhowerMatrix.ImportantNotUrgentList.add(txtField.getText());
        });
        importantUrgentVBox.getChildren().forEach(node -> {
            TextField txtField = (TextField) node;
            eisenhowerMatrix.ImportantUrgentList.add(txtField.getText());
        });
        notImportantNotUrgentVBox.getChildren().forEach(node -> {
            TextField txtField = (TextField) node;
            eisenhowerMatrix.NotImportantNotUrgentList.add(txtField.getText());
        });
        notImportantUrgentVBox.getChildren().forEach(node -> {
            TextField txtField = (TextField) node;
            eisenhowerMatrix.NotImportantUrgentList.add(txtField.getText());
        });
        return eisenhowerMatrix;
    }

    public void onAddImportantUrgentClicked(ActionEvent actionEvent) {
        TextField txt = new TextField();
        txt.setAlignment(Pos.CENTER);
        VBox parentVbox = (VBox) ((Button) actionEvent.getSource()).getParent();
        VBox taskList = (VBox) parentVbox.getChildren().get(1);
        taskList.getChildren().add(txt);
        //eisenhowerMatrix.ImportantUrgentList.add(txt.getText());
    }

    public void onNotImportantUrgentClicked(ActionEvent actionEvent) {
        TextField txt = new TextField();
        txt.setAlignment(Pos.CENTER);
        VBox parentVbox = (VBox) ((Button) actionEvent.getSource()).getParent();
        VBox taskList = (VBox) parentVbox.getChildren().get(0);
        taskList.getChildren().add(txt);
        //eisenhowerMatrix.NotImportantUrgentList.add(txt.getText());
    }

    public void onAddImportantNotUrgentClicked(ActionEvent actionEvent) {
        TextField txt = new TextField();
        txt.setAlignment(Pos.CENTER);
        VBox parentVbox = (VBox) ((Button) actionEvent.getSource()).getParent();
        VBox taskList = (VBox) parentVbox.getChildren().get(1);
        taskList.getChildren().add(txt);
        //eisenhowerMatrix.ImportantNotUrgentList.add(txt.getText());
    }

    public void onNotImportantNotUrgentClicked(ActionEvent actionEvent) {
        TextField txt = new TextField();
        txt.setAlignment(Pos.CENTER);
        VBox parentVbox = (VBox) ((Button) actionEvent.getSource()).getParent();
        VBox taskList = (VBox) parentVbox.getChildren().get(0);
        taskList.getChildren().add(txt);
        //eisenhowerMatrix.NotImportantNotUrgentList.add(txt.getText());
    }
}
