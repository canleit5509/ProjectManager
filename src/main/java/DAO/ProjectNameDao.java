package DAO;

import DAO.DAO;
import Model.ProjectName;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class ProjectNameDao implements DAO<ProjectName> {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/projectmanager";
    private static final String ID = "root";
    private static final String PASS = "192025509Aa";
    Connection connection;
    private static final String DELETE = "DELETE FROM projectname WHERE projectName=?";
    private static final String FIND_ALL = "SELECT * FROM projectname ORDER BY projectName";
    private static final String FIND_ALL_NOW = "SELECT * FROM projectname WHERE done=false ORDER BY projectName";
    private static final String FIND_ALL_DONE = "SELECT * FROM projectname WHERE done=true ORDER BY projectName";
    private static final String FIND_BY_ID = "SELECT * FROM projectName WHERE projectName=?";
    private static final String INSERT = "INSERT INTO projectname(projectName, projectColor, done) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE projectname SET projectColor=?, done=? WHERE projectName=?";
    PreparedStatement preparedStatement;

    public ProjectNameDao() {
        connection = getConnection();
    }

    @Override
    public ArrayList<ProjectName> getAll() {
        ArrayList<ProjectName> projectNames = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet RS = preparedStatement.executeQuery();
            while (RS.next()) {
                ProjectName projectName = new ProjectName(RS.getString("projectName"), RS.getString("projectColor"), RS.getInt("done"));
                projectNames.add(projectName);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(preparedStatement);
        }
        return projectNames;
    }

    public ArrayList<ProjectName> getAllNow() {
        ArrayList<ProjectName> projectNames = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(FIND_ALL_NOW);
            ResultSet RS = preparedStatement.executeQuery();
            while (RS.next()) {
                ProjectName projectName = new ProjectName(RS.getString("projectName"), RS.getString("projectColor"), RS.getInt("done"));
                projectNames.add(projectName);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(preparedStatement);
        }
        return projectNames;
    }

    public ArrayList<ProjectName> getAllDone() {
        ArrayList<ProjectName> projectNames = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(FIND_ALL_DONE);
            ResultSet RS = preparedStatement.executeQuery();
            while (RS.next()) {
                ProjectName projectName = new ProjectName(RS.getString("projectName"), RS.getString("projectColor"), RS.getInt("done"));
                projectNames.add(projectName);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(preparedStatement);
        }
        return projectNames;
    }

    @Override
    public ProjectName get(String id) {
        try {
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setString(1, id);
            ResultSet RS = preparedStatement.executeQuery();
            ProjectName projectName = null;
            while (RS.next()) {
                 projectName = new ProjectName(RS.getString("projectName"), RS.getString("projectColor"), RS.getInt("done"));
            }
            return projectName;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(ProjectName project) {
        try {
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setString(2, project.getProjectColor());
            preparedStatement.setInt(3, project.getDone());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public void update(ProjectName project) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, project.getProjectColor());
            preparedStatement.setInt(2, project.getDone());
            preparedStatement.setString(3, project.getProjectName());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public void delete(ProjectName projectName) {
        try {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(1, projectName.getProjectName());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public ArrayList<String> getAllName() {
        ArrayList<String> projectNames = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet RS = preparedStatement.executeQuery();
            while (RS.next()) {
                String s = RS.getString("projectName");
                projectNames.add(s);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(preparedStatement);
        }
        return projectNames;
    }

    public ArrayList<String> getAllNameNow() {
        ArrayList<String> projectNames = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(FIND_ALL_NOW);
            ResultSet RS = preparedStatement.executeQuery();
            while (RS.next()) {
                String s = RS.getString("projectName");
                projectNames.add(s);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(preparedStatement);
        }
        return projectNames;
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
