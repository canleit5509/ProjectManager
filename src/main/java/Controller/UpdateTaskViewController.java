package Controller;


import Model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.time.LocalDate;

public class UpdateTaskViewController {
    @FXML
    Label id;
    @FXML
    ComboBox<String> prName;
    @FXML
    ComboBox<String> name;
    @FXML
    TextField title;
    @FXML
    DatePicker startDate;
    @FXML
    DatePicker deadline;
    @FXML
    DatePicker finishDate;
    @FXML
    TextField expectedTime;
    @FXML
    TextField finishTime;
    @FXML
    TextField processed;

    public void setTask(Task task){
        //TODO: chinh database task.startDate, deadline, finishDate thanh Date
        id.setText(task.getId());
        prName.setPromptText(task.getPrName());
        name.setPromptText(task.getName());
        title.setText(task.getTitle());
        startDate.setValue(LocalDate.parse(task.getStartDate()));
        deadline.setValue(LocalDate.parse(task.getDeadline()));
        finishDate.setValue(LocalDate.parse(task.getFinishDate()));
        expectedTime.setText(task.getExpectedTime()+"");
        finishTime.setText(task.getFinishTime()+"");
        processed.setText(task.getProcessed()+"");
    }

    public void setComboBox(){
        DatabaseConnector dbConn = new DatabaseConnector();
        ObservableList<String> prList = FXCollections.observableArrayList(dbConn.getAllProjectName());
        ObservableList<String> personList = FXCollections.observableArrayList(dbConn.getAllPersonName());
        prName.setItems(prList);
        name.setItems(personList);
    }

    public void cancel(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void AddProject(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/AddProject.fxml"));
        Parent addProject = loader.load();
        Scene scene = new Scene(addProject);
        Stage addProjectWindow = new Stage();
        addProjectWindow.setTitle("Thêm dự án");
        addProjectWindow.setScene(scene);
        addProjectWindow.initModality(Modality.WINDOW_MODAL);
        addProjectWindow.initOwner(stage);
        addProjectWindow.showAndWait();
        setComboBox();
    }

    public void AddPerson(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/AddPerson.fxml"));
        Parent addProject = loader.load();
        Scene scene = new Scene(addProject);
        Stage addProjectWindow = new Stage();
        addProjectWindow.setTitle("Thêm nhân sự");
        addProjectWindow.setScene(scene);
        addProjectWindow.initModality(Modality.WINDOW_MODAL);
        addProjectWindow.initOwner(stage);
        AddPerson addPerson = loader.getController();
        addPerson.setID();
        addProjectWindow.showAndWait();
        setComboBox();
    }
}
