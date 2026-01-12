package com.example.maverick;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;

public class KanbanViewController {

    @FXML public VBox todoVbox;
    @FXML public VBox ongoingVbox;
    @FXML public VBox doneVbox;

    private MainWindowController mainWindowController;

    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setMainWindowController(MainWindowController _mainWindowController) {
        mainWindowController = _mainWindowController;
    }

    public KanbanBoard getKanbanBoard() {
        KanbanBoard board = new KanbanBoard();

        todoVbox.getChildren().forEach(node -> {
            HBox hbox = (HBox) node;
            TextField tf = (TextField) hbox.getChildren().getFirst();
            board.TODOList.add(tf.getText());
        });

        ongoingVbox.getChildren().forEach(node -> {
            HBox hbox = (HBox) node;
            TextField tf = (TextField) hbox.getChildren().getFirst();
            board.OngoingList.add(tf.getText());
        });

        doneVbox.getChildren().forEach(node -> {
            HBox hbox = (HBox) node;
            TextField tf = (TextField) hbox.getChildren().getFirst();
            board.DoneList.add(tf.getText());
        });

        return board;
    }

    public void setKanbanBoard(KanbanBoard board) {
        todoVbox.getChildren().clear();
        ongoingVbox.getChildren().clear();
        doneVbox.getChildren().clear();

        board.TODOList.forEach(task -> addKanbanCard(task, todoVbox));
        board.OngoingList.forEach(task -> addKanbanCard(task, ongoingVbox));
        board.DoneList.forEach(task -> addKanbanCard(task, doneVbox));
    }

    private void addKanbanCard(String text, VBox target) {
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("kanban-card.fxml"));
        Parent card;
        try {
            card = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CardViewController cardViewController = loader.getController();
        cardViewController.setMainViewController(mainWindowController);
        cardViewController.setKanbanController(this);

        HBox hbox = (HBox) card;
        TextField tf = (TextField) hbox.getChildren().getFirst();
        tf.setText(text);

        target.getChildren().add(card);
    }

    @FXML
    protected void onTodoAddCardClicked(ActionEvent event) throws IOException {
        addEmptyCard(todoVbox);
    }

    @FXML
    protected void onOngoingAddCardClicked(ActionEvent event) throws IOException {
        addEmptyCard(ongoingVbox);
    }

    @FXML
    protected void onDoneAddCardClicked(ActionEvent event) throws IOException {
        addEmptyCard(doneVbox);
    }

    private void addEmptyCard(VBox target) throws IOException {
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("kanban-card.fxml"));
        Parent card = loader.load();
        CardViewController cardViewController = loader.getController();
        cardViewController.setMainViewController(mainWindowController);
        cardViewController.setKanbanController(this);
        target.getChildren().add(card);
    }

    public void loadFromDb() throws SQLException {
        KanbanBoard board = model.loadKanban();
        setKanbanBoard(board);
    }
}
