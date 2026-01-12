package com.example.maverick;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class CardViewController {
    private MainWindowController mainWindowController;
    private KanbanViewController kanbanController;

    public void setKanbanController(KanbanViewController controller) {
        this.kanbanController = controller;
    }

    public void setMainViewController (MainWindowController _mainWindowController) {
        mainWindowController = _mainWindowController;
    }

    public void onDeleteButtonClicked(ActionEvent actionEvent) {
        HBox hbox = (HBox) ((Button) actionEvent.getSource()).getParent().getParent();
        VBox hboxContainer = (VBox) hbox.getParent();
        hboxContainer.getChildren().remove(hbox);
    }

/*    public void onMoveRightButtonClicked(ActionEvent evt) {
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
    }*/


/*
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
*/

    public void onMoveRightButtonClicked(ActionEvent evt) {
        HBox card = (HBox) ((Button) evt.getSource()).getParent().getParent();
        VBox currentBox = (VBox) card.getParent();

        if (currentBox == kanbanController.todoVbox) {
            kanbanController.todoVbox.getChildren().remove(card);
            kanbanController.ongoingVbox.getChildren().add(card);
        } else if (currentBox == kanbanController.ongoingVbox) {
            kanbanController.ongoingVbox.getChildren().remove(card);
            kanbanController.doneVbox.getChildren().add(card);
        }
    }

    public void onMoveLeftButtonClicked(ActionEvent evt) {
        HBox card = (HBox) ((Button) evt.getSource()).getParent().getParent();
        VBox currentBox = (VBox) card.getParent();

        if (currentBox == kanbanController.doneVbox) {
            kanbanController.doneVbox.getChildren().remove(card);
            kanbanController.ongoingVbox.getChildren().add(card);
        } else if (currentBox == kanbanController.ongoingVbox) {
            kanbanController.ongoingVbox.getChildren().remove(card);
            kanbanController.todoVbox.getChildren().add(card);
        }
    }


}
