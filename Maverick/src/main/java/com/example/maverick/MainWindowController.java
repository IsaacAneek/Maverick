package com.example.maverick;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class MainWindowController {
    @FXML private VBox todoVbox;
    @FXML private VBox ongoingVbox;
    @FXML private VBox doneVbox;
    @FXML private HBox dashboardHbox;
    @FXML private VBox miscVbox;

    @FXML
    protected void onTodoAddCardClicked(ActionEvent event) {
        TextField text = new TextField();
        text.setPrefWidth(todoVbox.getWidth() - 10);
        text.setMaxWidth(Double.MAX_VALUE);
        todoVbox.getChildren().add(text);
    }

    @FXML
    protected void onOngoingAddCardClicked(ActionEvent event) {
        TextField text = new TextField();
        text.setPrefWidth(todoVbox.getWidth() - 10);
        text.setMaxWidth(Double.MAX_VALUE);
        ongoingVbox.getChildren().add(text);
    }

    @FXML
    protected void onDoneAddCardClicked(ActionEvent event) {
        TextField text = new TextField();
        text.setPrefWidth(todoVbox.getWidth() - 10);
        text.setMaxWidth(Double.MAX_VALUE);
        doneVbox.getChildren().add(text);
    }

    @FXML
    protected void onAddAnotherListClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("another-list.fxml"));
        Node loadedView = (Node) fxmlLoader.load();
        //loadedView.maxWidth(Double.MAX_VALUE);
        dashboardHbox.getChildren().add(loadedView);
    }

}
