package com.example.maverick;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import org.w3c.dom.Text;

import java.awt.*;
import java.io.IOException;

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

    public void setEisenhowerMatrix(EisenhowerMatrix matrix) {
        importantUrgentVBox.getChildren().clear();
        importantNotUrgentVBox.getChildren().clear();
        notImportantUrgentVBox.getChildren().clear();
        notImportantNotUrgentVBox.getChildren().clear();

        matrix.ImportantUrgentList.forEach(taskName -> addEisenhowerCard(taskName, importantUrgentVBox));
        IO.println("Important urgent loaded");

        matrix.ImportantNotUrgentList.forEach(taskName -> addEisenhowerCard(taskName, importantNotUrgentVBox));
        matrix.NotImportantUrgentList.forEach(taskName -> addEisenhowerCard(taskName, notImportantUrgentVBox));
        matrix.NotImportantNotUrgentList.forEach(taskName -> addEisenhowerCard(taskName, notImportantNotUrgentVBox));
    }

    private void addEisenhowerCard(String taskName, VBox vbox) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("eisenhower-card.fxml"));
        Parent card;
        try {
            card = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HBox cardHbox = (HBox) card;
        TextField textField = (TextField) cardHbox.getChildren().getFirst();
        textField.setText(taskName);
        importantUrgentVBox.getChildren().add((Node)card);
    }


    public void onAddImportantUrgentClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("eisenhower-card.fxml"));
        Parent card = fxmlLoader.load();

        HBox cardHbox = (HBox) card;
        TextField textField = (TextField) cardHbox.getChildren().getFirst();
        ColorPicker colorPicker = (ColorPicker) cardHbox.getChildren().get(1);
        colorPicker.setOnAction(evt -> {
            textField.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), new CornerRadii(3.00), null)));
        });

        VBox parentVbox = (VBox) ((Button) actionEvent.getSource()).getParent();
        VBox taskList = (VBox) parentVbox.getChildren().get(1);
        taskList.getChildren().add(card);
        //eisenhowerMatrix.ImportantUrgentList.add(txt.getText());
    }

    public void onNotImportantUrgentClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("eisenhower-card.fxml"));
        Parent card = fxmlLoader.load();

        HBox cardHbox = (HBox) card;
        TextField textField = (TextField) cardHbox.getChildren().getFirst();
        ColorPicker colorPicker = (ColorPicker) cardHbox.getChildren().get(1);
        colorPicker.setOnAction(evt -> {
            textField.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), new CornerRadii(3.00), null)));
        });

        VBox parentVbox = (VBox) ((Button) actionEvent.getSource()).getParent();
        VBox taskList = (VBox) parentVbox.getChildren().get(0);
        taskList.getChildren().add(card);
        //eisenhowerMatrix.NotImportantUrgentList.add(txt.getText());
    }

    public void onAddImportantNotUrgentClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("eisenhower-card.fxml"));
        Parent card = fxmlLoader.load();

        HBox cardHbox = (HBox) card;
        TextField textField = (TextField) cardHbox.getChildren().getFirst();
        ColorPicker colorPicker = (ColorPicker) cardHbox.getChildren().get(1);
        colorPicker.setOnAction(evt -> {
            textField.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), new CornerRadii(3.00), null)));
        });

        VBox parentVbox = (VBox) ((Button) actionEvent.getSource()).getParent();
        VBox taskList = (VBox) parentVbox.getChildren().get(1);
        taskList.getChildren().add(card);
        //eisenhowerMatrix.ImportantNotUrgentList.add(txt.getText());
    }

    public void onNotImportantNotUrgentClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("eisenhower-card.fxml"));
        Parent card = fxmlLoader.load();

        HBox cardHbox = (HBox) card;
        TextField textField = (TextField) cardHbox.getChildren().getFirst();
        ColorPicker colorPicker = (ColorPicker) cardHbox.getChildren().get(1);
        colorPicker.setOnAction(evt -> {
            textField.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), new CornerRadii(3.00), null)));
        });

        VBox parentVbox = (VBox) ((Button) actionEvent.getSource()).getParent();
        VBox taskList = (VBox) parentVbox.getChildren().get(0);
        taskList.getChildren().add(card);
        //eisenhowerMatrix.NotImportantNotUrgentList.add(txt.getText());
    }
}
