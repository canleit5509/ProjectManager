package Controller;

import Model.ProjectName;
import Model.Task;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryViewController implements Initializable {
    @FXML
    TableView<Task> tbData;
    @FXML
    TableColumn<Task, String> tcProjectName;
    @FXML
    TableColumn<Task, String> tcTask;
    @FXML
    TableColumn<Task, String> tcNgPTr;
    @FXML
    TableColumn<Task, String> tcDateStart;
    @FXML
    TableColumn<Task, String> tcDeadline;
    @FXML
    TableColumn<Task, String> tcFinishDate;
    @FXML
    TableColumn<Task, String> tcExpectedTime;
    @FXML
    TableColumn<Task, String> tcFinishTime;
    @FXML
    TableColumn<Task, String> tcProcess;
    private ObservableList<Task> listTask;


    public PrimaryViewController(){
        Task a = new Task("Project", "Task", "Nguoi phu trach","5/5/5", "6/6/6");
        listTask = FXCollections.observableArrayList();
        listTask.add(a);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tcProjectName.setCellValueFactory(new PropertyValueFactory<Task, String>("prName"));
        tcTask.setCellValueFactory(new PropertyValueFactory<Task, String>("title"));
        tcNgPTr.setCellValueFactory(new PropertyValueFactory<Task, String>("phuTrach"));
        tcDateStart.setCellValueFactory(new PropertyValueFactory<Task, String>("startDate"));
        tcDeadline.setCellValueFactory(new PropertyValueFactory<Task, String>("deadline"));
        tcFinishDate.setCellValueFactory(new PropertyValueFactory<Task, String>("finishDate"));
        tcExpectedTime.setCellValueFactory(new PropertyValueFactory<Task, String>("expectedTime"));
        tcFinishTime.setCellValueFactory(new PropertyValueFactory<Task, String>("finishTime"));
        tcProcess.setCellValueFactory(new PropertyValueFactory<Task, String>("process"));
        tbData.setItems(listTask);
        tbData.setEditable(true);
    }
}