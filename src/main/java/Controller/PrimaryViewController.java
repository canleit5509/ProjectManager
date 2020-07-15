package Controller;

import Model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
    private DatabaseConnector dbConn;


    public PrimaryViewController(){
        Task a = new Task("Project", "Task", "Nguoi phu trach","5/5/5", "6/6/6");
        dbConn = new DatabaseConnector();
        listTask = FXCollections.observableArrayList(dbConn.getAllTask());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tcProjectName.setCellValueFactory(new PropertyValueFactory<Task, String>("prName"));
        tcTask.setCellValueFactory(new PropertyValueFactory<Task, String>("title"));
        tcNgPTr.setCellValueFactory(new PropertyValueFactory<Task, String>("name"));
        tcDateStart.setCellValueFactory(new PropertyValueFactory<Task, String>("startDate"));
        tcDeadline.setCellValueFactory(new PropertyValueFactory<Task, String>("deadline"));
        tcFinishDate.setCellValueFactory(new PropertyValueFactory<Task, String>("finishDate"));
        tcExpectedTime.setCellValueFactory(new PropertyValueFactory<Task, String>("expectedTime"));
        tcFinishTime.setCellValueFactory(new PropertyValueFactory<Task, String>("finishTime"));
        tcProcess.setCellValueFactory(new PropertyValueFactory<Task, String>("processed"));
        tbData.setItems(listTask);
        tbData.setEditable(true);
    }
    //TODO:
    public void addTask(Event e) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/AddTaskView.fxml"));
        Parent addTaskParent = loader.load();
        Scene scene = new Scene(addTaskParent);
        Stage addTaskWindow = new Stage();
        addTaskWindow.setTitle("Thêm công việc");
        addTaskWindow.setScene(scene);
        addTaskWindow.initModality(Modality.WINDOW_MODAL);
        addTaskWindow.initOwner(stage);
        addTaskWindow.showAndWait();
    }

    public void updateTask(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/UpdateTaskView.fxml"));
        Parent updateTaskParent = loader.load();
        Scene scene = new Scene(updateTaskParent);
        UpdateTaskViewController controller = loader.getController();
        Task selected = (Task) tbData.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Vui lòng chọn công việc cần chỉnh sửa");
            alert.show();
        } else {
            controller.setTask(selected);
            controller.setComboBox();
            Stage updateTaskWindow = new Stage();
            updateTaskWindow.setTitle("Chỉnh sửa công việc");
            updateTaskWindow.setScene(scene);
            updateTaskWindow.initModality(Modality.WINDOW_MODAL);
            updateTaskWindow.initOwner(stage);
            updateTaskWindow.showAndWait();
        }
    }
}
