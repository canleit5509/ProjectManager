package Controller;

import DAO.ProjectNameDao;
import DAO.TaskDao;
import Model.ProjectName;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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

    public PrimaryViewController() {
        TaskDao taskDao = new TaskDao();
        listTask = FXCollections.observableArrayList(taskDao.getAll());
    }

    public void RefreshTable() {
        TaskDao taskDao = new TaskDao();
        ObservableList<Task> taskList = FXCollections.observableArrayList(taskDao.getAll());
        tbData.setItems(taskList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tcProjectName.setCellValueFactory(new PropertyValueFactory<>("prName"));
        tcTask.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcNgPTr.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcDateStart.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tcDeadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        tcFinishDate.setCellValueFactory(new PropertyValueFactory<>("finishDate"));
        tcExpectedTime.setCellValueFactory(new PropertyValueFactory<>("expectedTime"));
        tcFinishTime.setCellValueFactory(new PropertyValueFactory<>("finishTime"));
        tcProcess.setCellValueFactory(new PropertyValueFactory<>("processed"));
        tbData.setItems(listTask);
        tbData.setEditable(true);
        tcProjectName.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Task, String> call(TableColumn<Task, String> taskStringTableColumn) {
                return new TableCell<>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            ProjectNameDao projectNameDao = new ProjectNameDao();
                            ProjectName name = projectNameDao.get(item);
                            this.setStyle("-fx-background-color: #" + name.getProjectColor().substring(2) + ";");
                            // Get fancy and change color based on data
                            if (item.contains("@"))
                                this.setTextFill(Color.BLUEVIOLET);
                            setText(item);
                            System.out.println(item);
                        }
                    }
                };
            }
        });
    }

    //TODO:
    public void addTask(Event e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
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
        RefreshTable();
    }

    public void updateTask(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/UpdateTaskView.fxml"));
        Parent updateTaskParent = loader.load();
        Scene scene = new Scene(updateTaskParent);
        UpdateTaskViewController controller = loader.getController();
        Task selected = tbData.getSelectionModel().getSelectedItem();
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
            RefreshTable();
        }
    }

    public void Delete(ActionEvent e) {
        Task selected = (Task) tbData.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Vui lòng chọn công việc cần xóa");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xóa công việc");
            alert.setHeaderText("Bạn có chắc chắn muốn xóa công việc này?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == null) {
            } else if (option.get() == ButtonType.OK) {
                tbData.getItems().remove(selected);
                TaskDao taskDao = new TaskDao();
                taskDao.delete(selected);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Thông báo");
                alert2.setHeaderText("Đã xóa");
                alert2.show();
            } else if (option.get() == ButtonType.CANCEL) {
            }
        }
    }

    public void btnProject(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ProjectManagement.fxml"));
        Parent addTaskParent = loader.load();
        Scene scene = new Scene(addTaskParent);
        Stage addTaskWindow = new Stage();
        addTaskWindow.setTitle("Quản lý nhân sự");
        addTaskWindow.setScene(scene);
        addTaskWindow.initModality(Modality.WINDOW_MODAL);
        addTaskWindow.initOwner(stage);
        addTaskWindow.showAndWait();
        RefreshTable();
    }

    public void btnPerson(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/PersonManagement.fxml"));
        Parent addTaskParent = loader.load();
        Scene scene = new Scene(addTaskParent);
        Stage addTaskWindow = new Stage();
        addTaskWindow.setTitle("Quản lý nhân sự");
        addTaskWindow.setScene(scene);
        addTaskWindow.initModality(Modality.WINDOW_MODAL);
        addTaskWindow.initOwner(stage);
        addTaskWindow.showAndWait();
    }
}
