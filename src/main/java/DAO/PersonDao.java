package DAO;

import DAO.DAO;
import Model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class PersonDao implements DAO<Person> {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/projectmanager";
    private static final String ID = "project";
    private static final String PASS = "";

    private static final String DELETE = "DELETE FROM person WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM person ORDER BY id";
    private static final String FIND_ALL_RETIRED = "SELECT * FROM person WHERE retired=? ORDER BY id";
    private static final String FIND_BY_ID = "SELECT * FROM person WHERE id=?";
    private static final String FIND_BY_NAME = "SELECT * FROM person WHERE name=?";
    private static final String INSERT = "INSERT INTO person(id, name, color, retired) VALUES(?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE person SET name=?, color=?, retired=? WHERE id=?";
    /*TODO: lifecycle for DAO
     *
     */
    private PreparedStatement preparedStatement;
    private Connection connection;
    private ArrayList<Person> listPeople;

    public PersonDao() {
        connection = getConnection();
        listPeople = new ArrayList<>();
    }

    @Override
    public ArrayList<Person> getAll() {
        listPeople.clear();
        try {
            preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet RS = preparedStatement.executeQuery();
            while (RS.next()) {
                Person person = new Person(RS.getString("id"), RS.getString("name"), RS.getString("color"), RS.getInt("retired"));
                listPeople.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(preparedStatement);
        }
        return listPeople;
    }

    public ArrayList<String> getDoingIdName() {
        ArrayList<String> listPeopleName = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(FIND_ALL_RETIRED);
            preparedStatement.setInt(1, 0);
            ResultSet RS = preparedStatement.executeQuery();
            while (RS.next()) {
                String id = RS.getString("id");
                String name = RS.getString("name");
                listPeopleName.add(id + "|" + name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(preparedStatement);
        }
        return listPeopleName;
    }

    public ArrayList<Person> getRetiredPerson(int check) {
        ArrayList<Person> listPeople = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(FIND_ALL_RETIRED);
            preparedStatement.setInt(1,check);
            ResultSet RS = preparedStatement.executeQuery();
            while (RS.next()) {
                Person person = new Person(RS.getString("id"), RS.getString("name"), RS.getString("color"), RS.getInt("retired"));
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
    public Person get(String name) {
        try{
            preparedStatement = connection.prepareStatement(FIND_BY_NAME);
            preparedStatement.setString(1,name);
            ResultSet RS = preparedStatement.executeQuery();
            while(RS.next()){
                Person person = new Person(RS.getString("id"), RS.getString("name"), RS.getString("color"), RS.getInt("retired"));
                return person;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Person getByID(String id) {
        try{
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setString(1,id);
            ResultSet RS = preparedStatement.executeQuery();
            while(RS.next()){
                Person person = new Person(RS.getString("id"), RS.getString("name"), RS.getString("color"), RS.getInt("retired"));
                return person;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    @Override
    public void add(Person person) {
        try {
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, person.getId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getColor());
            preparedStatement.setInt(4, person.getRetired());
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
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getColor());
            preparedStatement.setInt(3, person.getRetired());
            preparedStatement.setString(4, person.getId());
            preparedStatement.executeUpdate();
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
        try {
            preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet RS = preparedStatement.executeQuery();
            while (RS.next()) {
                String id = RS.getString("id");
                String name = RS.getString("name");
                projectNames.add(id + "|" + name);
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
