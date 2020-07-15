package Controller;

import Model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class PersonDao implements DAO<Person> {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/projectmanager";
    private static final String ID = "root";
    private static final String PASS = "192025509Aa";
    /*TODO: lifecycle for DAO
    *
    */
    private Connection connection;
    public PersonDao() {
        connection = getConnection();
    }

    @Override
    public ArrayList<Person> getAll() {
        ArrayList<Person> listPeople = new ArrayList<>();

        Statement statement = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM person";
            statement = connection.createStatement();
            ResultSet RS = statement.executeQuery(sql);
            while (RS.next()) {
                Person person = new Person(RS.getString("id"), RS.getString("name"), RS.getString("color"));
                listPeople.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(statement);
        }
        return listPeople;
    }

    @Override
    public Optional<Person> get(String id) {
        return Optional.empty();
    }

    @Override
    public void add(Person person) {
        Statement statement = null;
        try {
            connection = getConnection();
            String sql = "insert into person values('" + person.getId() + "','" + person.getName() + "','" + person.getColor() + "')";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(statement);
        }
    }

    @Override
    public void update(Person person) {
        Statement statement = null;
        try {
            connection = getConnection();
            String sql = "update person set name= '" + person.getName() + "', color= '"
                    + person.getColor() + "' where id='" + person.getId() + "'";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(statement);
        }
    }

    @Override
    public void delete(Person person) {
        Statement statement = null;
        try {
            connection = getConnection();
            String sql = " delete from person where id = '" + person.getId() + "'";
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(statement);
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
