package DAO;

import DAO.DAO;
import Model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class TaskDao implements DAO<Task> {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/projectmanager";
    private static final String ID = "root";
    private static final String PASS = "192025509Aa";

    private static final String DELETE = "DELETE FROM task WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM task ORDER BY id";
    private static final String FIND_BY_ID = "SELECT * FROM task WHERE id=?";
    private static final String FIND_BY_NAME = "SELECT * FROM task WHERE name=?";
    private static final String INSERT = "INSERT INTO task(id, projectName, title, name, startDate, deadline, finishDate," +
            "expectTime, finishTime, processed) VALUES(?, ?, ?, ?, ? ,?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE task SET projectName=?, title=?, name=?, startDate=?, deadline=?, " +
            "finishDate=?, expectTime=?, finishTime=?, processed=? WHERE id=?";
    private Connection connection;
    private PreparedStatement preparedStatement;

    public TaskDao() {
        connection = getConnection();
    }

    @Override
    public ArrayList<Task> getAll() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet RS = preparedStatement.executeQuery();
            while (RS.next()) {
                Task task = new Task(RS.getString("id"), RS.getString("projectName"), RS.getString("title"),
                        RS.getString("name"), RS.getString("startDate"), RS.getString("deadline"),
                        RS.getString("finishDate"), RS.getInt("expectTime"), RS.getInt("finishTime"),
                        RS.getInt("processed"));
                tasks.add(task);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(preparedStatement);
        }
        return tasks;
    }

    @Override
    public Optional<Task> get(String id) {
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Task task = new Task();
                task.setId(rs.getString("id"));
                task.setPrName(rs.getString("projectName"));
                task.setTitle(rs.getString("title"));
                task.setName(rs.getString("name"));
                task.setStartDate(rs.getString("startDate"));
                task.setDeadline(rs.getString("deadline"));
                task.setFinishDate(rs.getString("finishDate"));
                task.setExpectedTime(rs.getInt("expectTime"));
                task.setFinishTime(rs.getInt("finishTime"));
                task.setProcessed(rs.getInt("processed"));
                return Optional.of(task);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement);
        }

    }

    @Override
    public void add(Task task) {
        try {
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, task.getId());
            preparedStatement.setString(2, task.getPrName());
            preparedStatement.setString(3, task.getTitle());
            preparedStatement.setString(4, task.getName());
            preparedStatement.setString(5, task.getStartDate());
            preparedStatement.setString(6, task.getDeadline());
            preparedStatement.setString(7, task.getFinishDate());
            preparedStatement.setInt(8, task.getExpectedTime());
            preparedStatement.setInt(9, task.getFinishTime());
            preparedStatement.setInt(10, task.getProcessed());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public void update(Task task) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(10, task.getId());
            preparedStatement.setString(1, task.getPrName());
            preparedStatement.setString(2, task.getTitle());
            preparedStatement.setString(3, task.getName());
            preparedStatement.setString(4, task.getStartDate());
            preparedStatement.setString(5, task.getDeadline());
            preparedStatement.setString(6, task.getFinishDate());
            preparedStatement.setInt(7, task.getExpectedTime());
            preparedStatement.setInt(8, task.getFinishTime());
            preparedStatement.setInt(9, task.getProcessed());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public void delete(Task task) {

        try {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(1, task.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public ArrayList<String> getAllName() {
        return null;
    }

    private Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(DB_URL, ID, PASS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
