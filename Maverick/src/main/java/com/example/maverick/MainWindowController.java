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
    private void initialize() throws SQLException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kanban-view.fxml"));
        Parent loadedView = fxmlLoader.load();
        KanbanViewController kanbanViewController = fxmlLoader.getController();
        KanbanBoard kanbanBoard = model.loadKanban();
        kanbanViewController.setMainWindowController(this);
        kanbanViewController.setKanbanBoard(kanbanBoard);
        //kanbanView = dashboardHbox.getChildren();
        dashboardHbox.getChildren().setAll(loadedView);
    }

    public HBox getDashboardHbox() {
        return dashboardHbox;
    }

    @FXML
    protected void onTodoAddCardClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kanban-card.fxml"));
        HBox loadedView = (HBox) fxmlLoader.load();

        CardViewController cardViewController = fxmlLoader.getController();
        cardViewController.setMainViewController(this);

        todoVbox.getChildren().add(loadedView);
    }

    @FXML
    protected void onOngoingAddCardClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kanban-card.fxml"));
        HBox loadedView = (HBox) fxmlLoader.load();

        CardViewController cardViewController = fxmlLoader.getController();
        cardViewController.setMainViewController(this);

        ongoingVbox.getChildren().add(loadedView);
    }

    @FXML
    protected void onDoneAddCardClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kanban-card.fxml"));
        HBox loadedView = (HBox) fxmlLoader.load();

        CardViewController cardViewController = fxmlLoader.getController();
        cardViewController.setMainViewController(this);

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
    protected void onKanbanBoardButtonClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kanban-view.fxml"));
        Parent loadedView = fxmlLoader.load();
        KanbanViewController kanbanViewController = fxmlLoader.getController();
        KanbanBoard kanbanBoard = model.loadKanban();
        kanbanViewController.setMainWindowController(this);
        kanbanViewController.setKanbanBoard(kanbanBoard);
        //kanbanView = dashboardHbox.getChildren();
        dashboardHbox.getChildren().setAll(loadedView);
    }

    @FXML
    protected void onEisenhowerButtonClicked(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("eisenhower-view.fxml"));
        Parent loadedView = fxmlLoader.load();
        //kanbanView = dashboardHbox.getChildren();
        eisenhowerViewController = fxmlLoader.getController();

        EisenhowerMatrix matrix = model.loadEisenhower();
        eisenhowerViewController.setEisenhowerMatrix(matrix);

        dashboardHbox.getChildren().setAll(loadedView);
    }


    @FXML
    protected void onLoadClicked(ActionEvent event) throws IOException, SQLException {
        KanbanBoard kanbanBoard = model.loadKanban();
        kanbanBoard.TODOList.forEach(task -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kanban-card.fxml"));
            HBox loadedView = null;
            try {
                loadedView = (HBox) fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            TextField txtField = (TextField) loadedView.getChildren().get(0);
            txtField.setText(task);

            CardViewController cardViewController = fxmlLoader.getController();
            cardViewController.setMainViewController(this);

            todoVbox.getChildren().add(loadedView);
        });
        kanbanBoard.OngoingList.forEach(task -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kanban-card.fxml"));
            HBox loadedView = null;
            try {
                loadedView = (HBox) fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            TextField txtField = (TextField) loadedView.getChildren().get(0);
            txtField.setText(task);

            CardViewController cardViewController = fxmlLoader.getController();
            cardViewController.setMainViewController(this);

            ongoingVbox.getChildren().add(loadedView);
        });

        kanbanBoard.DoneList.forEach(task -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kanban-card.fxml"));
            HBox loadedView = null;
            try {
                loadedView = (HBox) fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            TextField txtField = (TextField) loadedView.getChildren().get(0);
            txtField.setText(task);

            CardViewController cardViewController = fxmlLoader.getController();
            cardViewController.setMainViewController(this);

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
