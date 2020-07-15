import Controller.DatabaseConnector;
import Controller.PersonDao;
import Controller.ProjectNameDao;
import Controller.TaskDao;
import Model.Person;
import Model.ProjectName;
import Model.Task;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/PrimaryView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Project Manage");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
