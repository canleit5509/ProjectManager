package Controller;

import Model.Person;
import Model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class TaskDao implements DAO<Task> {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/projectmanager";
    private static final String ID = "root";
    private static final String PASS = "";
    public TaskDao(){

    }
    @Override
    public ArrayList<Task> getAll() {
        ArrayList<Task> tasks = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM task";
            statement = connection.createStatement();
            ResultSet RS = statement.executeQuery(sql);
            while (RS.next()) {
                Task task = new Task(RS.getString("id"), RS.getString("projectName"), RS.getString("title"),
                        RS.getString("name"), RS.getString("startDate"), RS.getString("deadline"),
                        RS.getString("finishDate"), RS.getInt("expectTime"),RS.getInt("finishTime"),
                        RS.getInt("processed"));
                tasks.add(task);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return tasks;
    }

    @Override
    public Optional<Task> get(String id) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = getConnection();
            String sql = "SELECT * FROM task WHERE id = '" + id + "'";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

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
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }

    }

    @Override
    public void add(Task task) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            String sql = "insert into task values('" + task.getId() + "','" + task.getPrName() + "','" + task.getTitle()
                    + "','" + task.getName() + "','" + task.getStartDate() + "','" + task.getDeadline() + "','"
                    + task.getFinishDate() + "','" + task.getExpectedTime() + "','" + task.getFinishTime() + "','"
                    + task.getProcessed() + "')";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public void update(Task task) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            String sql = "update task set projectName='" + task.getPrName() + "', title='" + task.getTitle()
                    + "', name='" + task.getName() + "', startDate='" + task.getStartDate() + "', deadline='" + task.getDeadline() + "', finishDate='"
                    + task.getFinishDate() + "', expectTime=" + task.getExpectedTime() + ", finishTime=" + task.getFinishTime() + ", processed="
                    + task.getProcessed() + " where id='" + task.getId() + "'";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public void delete(Task task) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            String sql = " delete from task where id = '" + task.getId() + "'";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(statement);
            close(connection);
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
