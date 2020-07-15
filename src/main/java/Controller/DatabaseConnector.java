package Controller;

import Model.Person;
import Model.ProjectName;
import Model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnector {
    private Connection conn;

    public Connection getConnection() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmanager", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.conn;
    }

    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Task> getAllTask() {
        try {
            ArrayList<Task> list = new ArrayList<>();
            String sql = " select * from task";
            Statement sta = getConnection().createStatement();
            ResultSet RS = sta.executeQuery(sql);
            while (RS.next() == true) {
                Task task = new Task(RS.getString("id"), RS.getString("projectName"), RS.getString("title"), RS.getString("name"), RS.getString("startDate"), RS.getString("deadLine"), RS.getString("finishDate"), RS.getInt("expectTime"), RS.getInt("finishTime"), RS.getInt("processed"));
                list.add(task);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return null;
    }

    public ArrayList<String> getAllProjectName() {
        try {
            ArrayList<String> list = new ArrayList<>();
            String sql = " select projectName from projectname";
            Statement sta = getConnection().createStatement();
            ResultSet RS = sta.executeQuery(sql);
            while (RS.next() == true) {
                String s = RS.getString("projectName");
                list.add(s);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return null;
    }

    public ArrayList<String> getAllPersonName() {
        try {
            ArrayList<String> list = new ArrayList<>();
            String sql = " select id,name from person";
            Statement sta = getConnection().createStatement();
            ResultSet RS = sta.executeQuery(sql);
            while (RS.next() == true) {
                String id = RS.getString("id");
                String name = RS.getString("name");
                list.add(id+" | "+name);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return null;
    }

    public void addPerson(Person person) {
        try {
            String sql = "insert into person values('" + person.getId() + "','" + person.getName() + "','" + person.getColor() + "')";
            Statement sta = getConnection().createStatement();
            sta.executeUpdate(sql);
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addProject(ProjectName project) {
        try {
            String sql = "insert into projectname values('" + project.getProjectName() + "','" + project.getProjectColor() + "')";
            Statement sta = getConnection().createStatement();
            sta.executeUpdate(sql);
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addTask(Task task) {
        try {
            String sql = "insert into task values('" + task.getId() + "','" + task.getPrName() + "','" + task.getTitle()
                    + "','" + task.getName() + "','" + task.getStartDate() + "','" + task.getDeadline() + "','"
                    + task.getFinishDate() + "','" + task.getExpectedTime() + "','" + task.getFinishTime() + "','"
                    + task.getProcessed() + "')";
            Statement sta = getConnection().createStatement();
            sta.executeUpdate(sql);
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletePerson(String id) {
        try {
            String sql = " delete from person where id = '" + id + "'";
            Statement sta = getConnection().createStatement();
            sta.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteProject(String name) {
        try {
            String sql = " delete from projectname where projectName = '" + name + "'";
            Statement sta = getConnection().createStatement();
            sta.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteTask(String id) {
        try {
            String sql = " delete from task where id = '" + id + "'";
            Statement sta = getConnection().createStatement();
            sta.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePerson(Person person) {
        try {
            String sql = "update person set name= '" + person.getName() + "', color= '" + person.getColor() + "' where id='" + person.getId() + "'";
            Statement sta = getConnection().createStatement();
            sta.executeUpdate(sql);
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateProject(ProjectName project) {
        try {
            String sql = "update projectname set color= '" + project.getProjectColor() + "' where projectName = '" + project.getProjectName() + "'";
            Statement sta = getConnection().createStatement();
            sta.executeUpdate(sql);
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTask(Task task) {
        try {
            String sql = "update task set projectName='" + task.getPrName() + "', title='" + task.getTitle()
                    + "', name='" + task.getName() + "', startDate='" + task.getStartDate() + "', deadLine='" + task.getDeadline() + "', finishDate='"
                    + task.getFinishDate() + "', expectTime='" + task.getExpectedTime() + "', finishTime='" + task.getFinishTime() + "', processed='"
                    + task.getProcessed() + "' where id='" + task.getId() + "'";
            Statement sta = getConnection().createStatement();
            sta.executeUpdate(sql);
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//
//    public void test(){
//        try {
//            String sql = "select * from person";
//            System.out.println("truoc : " + this.conn);
//            Statement sta = getConnection().createStatement();
//
//            System.out.println("sau : " + this.conn);
//            ResultSet RS = sta.executeQuery(sql);
//            while (RS.next() == true) {
//                Person person = new Person(RS.getString("id"),RS.getString("name"),RS.getString("color"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        closeConnection();
//    }
}
