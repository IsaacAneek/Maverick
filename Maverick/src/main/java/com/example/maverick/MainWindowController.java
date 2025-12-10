package com.example.maverick;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class MainWindowController {
    @FXML private VBox todoVbox;
    @FXML private VBox ongoingVbox;
    @FXML private HBox dashboardHbox;
    @FXML private VBox miscVbox;

    @FXML
    protected void onTodoAddCardClicked(ActionEvent event) {
        Label label = new Label("New to do");
        label.setPrefWidth(todoVbox.getWidth() - 10);
        label.setMaxWidth(Double.MAX_VALUE);
        todoVbox.getChildren().add(label);
    }

    @FXML
    protected void onOngoingAddCardClicked(ActionEvent event) {

    }

    @FXML
    protected void onAddAnotherListClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("another-list.fxml"));
        Node loadedView = (Node) fxmlLoader.load();
        //loadedView.maxWidth(Double.MAX_VALUE);
        dashboardHbox.getChildren().add(loadedView);
    }

}
