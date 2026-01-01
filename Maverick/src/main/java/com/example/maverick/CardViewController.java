package com.example.maverick;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class CardViewController {
    private MainWindowController mainWindowController;

    public void setMainViewController (MainWindowController _mainWindowController) {
        mainWindowController = _mainWindowController;
    }

    public void onDeleteButtonClicked(ActionEvent actionEvent) {
        HBox hbox = (HBox) ((Button) actionEvent.getSource()).getParent().getParent();
        VBox hboxContainer = (VBox) hbox.getParent();
        hboxContainer.getChildren().remove(hbox);
    }

    public void onMoveRightButtonClicked(ActionEvent evt) {
        HBox dashboardHbox = mainWindowController.getDashboardHbox();
        Node currentClickedButton = (Node) evt.getSource();
        HBox currentTask = (HBox) currentClickedButton.getParent().getParent();
        ScrollPane pane = (ScrollPane) currentTask.getParent().getParent().getParent().getParent();
        VBox currentVBox = (VBox) pane.getParent();
        int currentVBoxIndex = dashboardHbox.getChildren().indexOf(currentVBox);
        VBox nextVbox =  (VBox) dashboardHbox.getChildren().get(currentVBoxIndex + 1);
        VBox nextList = (VBox) ((ScrollPane) nextVbox.getChildren().get(1)).getContent();
        nextList.getChildren().add(currentTask);
        System.out.println(pane.toString());
    }

    public void onMoveLeftButtonClicked(ActionEvent evt) {
        HBox dashboardHbox = mainWindowController.getDashboardHbox();
        Node currentClickedButton = (Node) evt.getSource();
        HBox currentTask = (HBox) currentClickedButton.getParent().getParent();
        ScrollPane pane = (ScrollPane) currentTask.getParent().getParent().getParent().getParent();
        VBox currentVBox = (VBox) pane.getParent();
        int currentVBoxIndex = dashboardHbox.getChildren().indexOf(currentVBox);
        VBox nextVbox =  (VBox) dashboardHbox.getChildren().get(currentVBoxIndex - 1);
        VBox nextList = (VBox) ((ScrollPane) nextVbox.getChildren().get(1)).getContent();
        nextList.getChildren().add(currentTask);
        System.out.println(pane.toString());
    }
}
