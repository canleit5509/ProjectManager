package Controller;

import Model.Person;
import Model.ProjectName;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class PersonDao implements DAO<Person> {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/projectmanager";
    private static final String ID = "root";
    private static final String PASS = "192025509Aa";

    private static final String DELETE = "DELETE FROM person WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM person ORDER BY id";
    private static final String FIND_BY_ID = "SELECT * FROM person WHERE id=?";
    private static final String FIND_BY_NAME = "SELECT * FROM person WHERE name=?";
    private static final String INSERT = "INSERT INTO person(id, name, color) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE person SET name=?, color=?, passwd=? WHERE id=?";
    /*TODO: lifecycle for DAO
    *
    */
    private PreparedStatement preparedStatement;
    private Connection connection;
    public PersonDao() {
        connection = getConnection();
    }

    @Override
    public ArrayList<Person> getAll() {
        ArrayList<Person> listPeople = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet RS = preparedStatement.executeQuery();
            while (RS.next()) {
                Person person = new Person(RS.getString("id"), RS.getString("name"), RS.getString("color"));
                listPeople.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(preparedStatement);
        }
        return listPeople;
    }

    @Override
    public Optional<Person> get(String id) {
        return Optional.empty();
    }

    @Override
    public void add(Person person) {
        try {
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, person.getId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getColor());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public void update(Person person) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1,person.getName());
            preparedStatement.setString(2,person.getColor());
            preparedStatement.setString(3, person.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public void delete(Person person) {
        try {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(1, person.getId());
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
        Statement statement = null;
        try {
            connection = getConnection();
            String sql = "SELECT id,name FROM person";
            statement = connection.createStatement();
            ResultSet RS = statement.executeQuery(sql);
            while (RS.next()) {
                String id = RS.getString("id");
                String name = RS.getString("name");
                projectNames.add(id+ "|" + name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(statement);
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
