package Controller;

import Model.Person;
import Model.ProjectName;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class ProjectNameDao implements DAO<ProjectName> {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/projectmanager";
    private static final String ID = "root";
    private static final String PASS = "192025509Aa";

    public ProjectNameDao(){

    }
    @Override
    public ArrayList<ProjectName> getAll() {
        ArrayList<ProjectName> projectNames = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM projectname";
            statement = connection.createStatement();
            ResultSet RS = statement.executeQuery(sql);
            while (RS.next()) {
                ProjectName projectName = new ProjectName(RS.getString("projectName"), RS.getString("projectColor"));
                projectNames.add(projectName);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return projectNames;
    }

    @Override
    public Optional<ProjectName> get(String id) {
        return Optional.empty();
    }

    @Override
    public void add(ProjectName project) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            String sql = "insert into projectname values('" + project.getProjectName() + "','" + project.getProjectColor() + "')";
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
    public void update(ProjectName project) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            String sql = "update projectname set color= '" + project.getProjectColor()
                    + "' where projectName = '" + project.getProjectName() + "'";
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
    public void delete(ProjectName projectName) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            String sql = " delete from projectname where projectName = '" + projectName.getProjectName() + "'";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
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
