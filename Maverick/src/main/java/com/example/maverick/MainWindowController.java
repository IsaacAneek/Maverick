package com.example.maverick;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.Serial;
import java.sql.SQLException;


public class MainWindowController {
    @FXML private VBox todoVbox;
    @FXML private VBox ongoingVbox;
    @FXML private VBox doneVbox;
    @FXML private HBox dashboardHbox;
    @FXML private VBox miscVbox;

    private EisenhowerViewController eisenhowerViewController;
    private ObservableList<Node> kanbanView;
    private Model model;

    public MainWindowController() throws SQLException {
        model = new Model();
    }

    @FXML
    private void initialize() throws SQLException {

    }

    @FXML
    protected void onTodoAddCardClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("card-view.fxml"));
        HBox loadedView = (HBox) fxmlLoader.load();
        Button btn = (Button) ((HBox) loadedView.getChildren().get(1)).getChildren().get(1);
        btn.setOnAction(evt -> {
            Node currentClickedButton = (Node) evt.getSource();
            HBox currentTask = (HBox) currentClickedButton.getParent().getParent();
            ScrollPane pane = (ScrollPane) currentTask.getParent().getParent().getParent().getParent();
            VBox currentVBox = (VBox) pane.getParent();
            int currentVBoxIndex = dashboardHbox.getChildren().indexOf(currentVBox);
            VBox nextVbox =  (VBox) dashboardHbox.getChildren().get(currentVBoxIndex + 1);
            VBox nextList = (VBox) ((ScrollPane) nextVbox.getChildren().get(1)).getContent();
            nextList.getChildren().add(currentTask);
            System.out.println(pane.toString());
        });
        todoVbox.getChildren().add(loadedView);
    }

    @FXML
    protected void onOngoingAddCardClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("card-view.fxml"));
        HBox loadedView = (HBox) fxmlLoader.load();
        Button btn = (Button) ((HBox) loadedView.getChildren().get(1)).getChildren().get(1);
        btn.setOnAction(evt -> {
            Node currentClickedButton = (Node) evt.getSource();
            HBox currentTask = (HBox) currentClickedButton.getParent().getParent();
            ScrollPane pane = (ScrollPane) currentTask.getParent().getParent().getParent().getParent();
            VBox currentVBox = (VBox) pane.getParent();
            int currentVBoxIndex = dashboardHbox.getChildren().indexOf(currentVBox);
            VBox nextVbox =  (VBox) dashboardHbox.getChildren().get(currentVBoxIndex + 1);
            VBox nextList = (VBox) ((ScrollPane) nextVbox.getChildren().get(1)).getContent();
            nextList.getChildren().add(currentTask);
            System.out.println(pane.toString());
        });
        ongoingVbox.getChildren().add(loadedView);
    }

    @FXML
    protected void onDoneAddCardClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("card-view.fxml"));
        HBox loadedView = (HBox) fxmlLoader.load();
        Button btn = (Button) ((HBox) loadedView.getChildren().get(1)).getChildren().get(1);
        btn.setOnAction(evt -> {
            Node currentClickedButton = (Node) evt.getSource();
            HBox currentTask = (HBox) currentClickedButton.getParent().getParent();
            ScrollPane pane = (ScrollPane) currentTask.getParent().getParent().getParent().getParent();
            VBox currentVBox = (VBox) pane.getParent();
            int currentVBoxIndex = dashboardHbox.getChildren().indexOf(currentVBox);
            VBox nextVbox =  (VBox) dashboardHbox.getChildren().get(currentVBoxIndex + 1);
            VBox nextList = (VBox) ((ScrollPane) nextVbox.getChildren().get(1)).getContent();
            nextList.getChildren().add(currentTask);
            System.out.println(pane.toString());
        });
        doneVbox.getChildren().add(loadedView);
    }

    @FXML
    protected void onAddAnotherListClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("another-list.fxml"));
        Node loadedView = (Node) fxmlLoader.load();
        //loadedView.maxWidth(Double.MAX_VALUE);
        dashboardHbox.getChildren().add(loadedView);
    }

    @FXML
    protected void onMoveToNextListClicked(ActionEvent event) throws IOException {
        Node currentClickedButton = (Node) event.getSource();
        HBox currentTask = (HBox) currentClickedButton.getParent();
        ScrollPane pane = (ScrollPane) currentTask.getParent().getParent().getParent().getParent();
        VBox currentVBox = (VBox) pane.getParent();
        int currentVBoxIndex = dashboardHbox.getChildren().indexOf(currentVBox);
        VBox nextVbox =  (VBox) dashboardHbox.getChildren().get(currentVBoxIndex + 1);
        VBox nextList = (VBox) ((ScrollPane) nextVbox.getChildren().get(1)).getContent();
        nextList.getChildren().add(currentTask);
        System.out.println(pane.toString());
    }

    @FXML
    protected void onKanbanBoardButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kanban-view.fxml"));
        Parent loadedView = fxmlLoader.load();
        //kanbanView = dashboardHbox.getChildren();
        dashboardHbox.getChildren().setAll(loadedView);
    }

    @FXML
    protected void onEisenhowerButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("eisenhower-view.fxml"));
        Parent loadedView = fxmlLoader.load();
        //kanbanView = dashboardHbox.getChildren();
        eisenhowerViewController = fxmlLoader.getController();
        dashboardHbox.getChildren().setAll(loadedView);
    }

    @FXML
    protected void onLoadClicked(ActionEvent event) throws IOException, SQLException {
        KanbanBoard kanbanBoard = model.loadKanban();
        kanbanBoard.TODOList.forEach(task -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("card-view.fxml"));
            HBox loadedView = null;
            try {
                loadedView = (HBox) fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            TextField txtField = (TextField) loadedView.getChildren().get(0);
            txtField.setText(task);
            Button btn = (Button) ((HBox) loadedView.getChildren().get(1)).getChildren().get(1);
            btn.setOnAction(evt -> {
                Node currentClickedButton = (Node) evt.getSource();
                HBox currentTask = (HBox) currentClickedButton.getParent().getParent();
                ScrollPane pane = (ScrollPane) currentTask.getParent().getParent().getParent().getParent();
                VBox currentVBox = (VBox) pane.getParent();
                int currentVBoxIndex = dashboardHbox.getChildren().indexOf(currentVBox);
                VBox nextVbox =  (VBox) dashboardHbox.getChildren().get(currentVBoxIndex + 1);
                VBox nextList = (VBox) ((ScrollPane) nextVbox.getChildren().get(1)).getContent();
                nextList.getChildren().add(currentTask);
                System.out.println(pane.toString());
            });
            todoVbox.getChildren().add(loadedView);
        });
        kanbanBoard.OngoingList.forEach(task -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("card-view.fxml"));
            HBox loadedView = null;
            try {
                loadedView = (HBox) fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            TextField txtField = (TextField) loadedView.getChildren().get(0);
            txtField.setText(task);
            Button btn = (Button) ((HBox) loadedView.getChildren().get(1)).getChildren().get(1);
            btn.setOnAction(evt -> {
                Node currentClickedButton = (Node) evt.getSource();
                HBox currentTask = (HBox) currentClickedButton.getParent().getParent();
                ScrollPane pane = (ScrollPane) currentTask.getParent().getParent().getParent().getParent();
                VBox currentVBox = (VBox) pane.getParent();
                int currentVBoxIndex = dashboardHbox.getChildren().indexOf(currentVBox);
                VBox nextVbox =  (VBox) dashboardHbox.getChildren().get(currentVBoxIndex + 1);
                VBox nextList = (VBox) ((ScrollPane) nextVbox.getChildren().get(1)).getContent();
                nextList.getChildren().add(currentTask);
                System.out.println(pane.toString());
            });
            ongoingVbox.getChildren().add(loadedView);
        });

        kanbanBoard.DoneList.forEach(task -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("card-view.fxml"));
            HBox loadedView = null;
            try {
                loadedView = (HBox) fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            TextField txtField = (TextField) loadedView.getChildren().get(0);
            txtField.setText(task);
            Button btn = (Button) ((HBox) loadedView.getChildren().get(1)).getChildren().get(1);
            btn.setOnAction(evt -> {
                Node currentClickedButton = (Node) evt.getSource();
                HBox currentTask = (HBox) currentClickedButton.getParent().getParent();
                ScrollPane pane = (ScrollPane) currentTask.getParent().getParent().getParent().getParent();
                VBox currentVBox = (VBox) pane.getParent();
                int currentVBoxIndex = dashboardHbox.getChildren().indexOf(currentVBox);
                VBox nextVbox =  (VBox) dashboardHbox.getChildren().get(currentVBoxIndex + 1);
                VBox nextList = (VBox) ((ScrollPane) nextVbox.getChildren().get(1)).getContent();
                nextList.getChildren().add(currentTask);
                System.out.println(pane.toString());
            });
            doneVbox.getChildren().add(loadedView);
        });
        System.out.println("Kanban loaded");
    }

    @FXML
    protected void onSaveClicked(ActionEvent event) throws IOException, SQLException {
        KanbanBoard kanbanBoard = new KanbanBoard();
        todoVbox.getChildren().forEach(hbox -> {
            HBox taskHbox = (HBox) hbox;
            String text = ((TextField) taskHbox.getChildren().get(0)).getText();
            kanbanBoard.TODOList.add(text);
        });
        ongoingVbox.getChildren().forEach(hbox -> {
            HBox taskHbox = (HBox) hbox;
            String text = ((TextField) taskHbox.getChildren().get(0)).getText();
            kanbanBoard.OngoingList.add(text);
        });
        doneVbox.getChildren().forEach(hbox -> {
            HBox taskHbox = (HBox) hbox;
            String text = ((TextField) taskHbox.getChildren().get(0)).getText();
            kanbanBoard.DoneList.add(text);
        });

        model.saveKanban(kanbanBoard);

        if(eisenhowerViewController != null) {
            model.saveEisenhower(eisenhowerViewController.getEisenhowerMatrix());
        }
    }

}
