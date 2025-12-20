package com.example.maverick;

import java.io.Console;
import java.io.Serial;
import java.sql.*;
import java.util.Objects;

public class Model {
    public Connection connection;

    public Model() throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        connection = databaseManager.getConnection();
        String kanban_table_SQL = "CREATE TABLE IF NOT EXISTS kanbanBoard (description TEXT, status TEXT NOT NULL)";
        Statement statement = connection.createStatement();
        statement.executeUpdate(kanban_table_SQL);
        String eisenhower_table_SQL = "CREATE TABLE IF NOT EXISTS eisenhowerMatrix (description TEXT, importance TEXT NOT NULL, urgentness TEXT NOT NULL)";
        statement = connection.createStatement();
        statement.executeUpdate(eisenhower_table_SQL);
    }

    public void saveEisenhower(EisenhowerMatrix eisenhowerMatrix) throws SQLException {
        String insert_SQL = "INSERT INTO eisenhowerMatrix (description, importance, urgentness) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert_SQL);
        eisenhowerMatrix.ImportantUrgentList.forEach(task -> {
            try {
                preparedStatement.setString(1, task);
                preparedStatement.setString(2, "IMPORTANT");
                preparedStatement.setString(3, "URGENT");
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        eisenhowerMatrix.ImportantNotUrgentList.forEach(task -> {
            try {
                preparedStatement.setString(1, task);
                preparedStatement.setString(2, "IMPORTANT");
                preparedStatement.setString(3, "NOTURGENT");
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        eisenhowerMatrix.NotImportantUrgentList.forEach(task -> {
            try {
                preparedStatement.setString(1, task);
                preparedStatement.setString(2, "NOTIMPORTANT");
                preparedStatement.setString(3, "URGENT");
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        eisenhowerMatrix.NotImportantNotUrgentList.forEach(task -> {
            try {
                preparedStatement.setString(1, task);
                preparedStatement.setString(2, "NOTIMPORTANT");
                preparedStatement.setString(3, "NOTURGENT");
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
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

    public KanbanBoard loadKanban() throws SQLException {
        KanbanBoard kanbanBoard = new KanbanBoard();
        String get_SQL = "SELECT * FROM kanbanBoard";
        PreparedStatement preparedStatement = connection.prepareStatement(get_SQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            if(Objects.equals(resultSet.getString("status"), "TODO")) {
                kanbanBoard.TODOList.add(resultSet.getString("description"));
                System.out.println("TODO");
            }
            else if(Objects.equals(resultSet.getString("status"), "ONGOING")) {
                kanbanBoard.OngoingList.add(resultSet.getString("description"));
            }
            else if(Objects.equals(resultSet.getString("status"), "DONE")) {
                kanbanBoard.DoneList.add(resultSet.getString("description"));
            }
        }
        return kanbanBoard;
    }
}
