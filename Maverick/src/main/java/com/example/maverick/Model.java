package com.example.maverick;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Model {
    public Connection connection;

    public Model() throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        connection = databaseManager.getConnection();
        String kanban_table_SQL = "CREATE TABLE IF NOT EXISTS kanbanBoard (description TEXT, status TEXT NOT NULL)";
        Statement statement = connection.createStatement();
        statement.executeUpdate(kanban_table_SQL);
    }

    public void saveKanban(KanbanBoard kanbanBoard) throws SQLException {
        String insert_SQL = "INSERT INTO kanbanBoard (description, status) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert_SQL);
        kanbanBoard.TODOList.forEach(task -> {
            try {
                preparedStatement.setString(1, task);
                preparedStatement.setString(2, "TODO");
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        kanbanBoard.OngoingList.forEach(task -> {
            try {
                preparedStatement.setString(1, task);
                preparedStatement.setString(2, "ONGOING");
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        kanbanBoard.DoneList.forEach(task -> {
            try {
                preparedStatement.setString(1, task);
                preparedStatement.setString(2, "DONE");
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
