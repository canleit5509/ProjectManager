package Controller;

import DAO.ProjectNameDao;
import DTO.PersonDTO;
import DTO.TaskDTO;
import Model.ProjectName;
import Service.PersonService;
import Service.TaskService;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class PrimaryViewController implements Initializable {
    @FXML
    Button btnEdit;
    @FXML
    TableView<TaskDTO> tbData;
    @FXML
    TableView tbDetailTask;
    @FXML
    TableColumn<TaskDTO, String> tcProjectName;
    @FXML
    TableColumn<TaskDTO, String> tcTask;
    @FXML
    TableColumn<TaskDTO, String> tcNgPTr;
    @FXML
    TableColumn<TaskDTO, String> tcDateStart;
    @FXML
    TableColumn<TaskDTO, String> tcDeadline;
    @FXML
    TableColumn<TaskDTO, String> tcFinishDate;
    @FXML
    TableColumn<TaskDTO, String> tcExpectedTime;
    @FXML
    TableColumn<TaskDTO, String> tcFinishTime;
    @FXML
    TableColumn<TaskDTO, String> tcProcess;
    private ObservableList<TaskDTO> listTask;
    TaskService taskService;
    PersonService personService;

    public PrimaryViewController() {
        taskService = new TaskService();
        personService = new PersonService();
        listTask = FXCollections.observableArrayList(taskService.getAllTask());
    }

    public void refreshTable() {
        ObservableList<TaskDTO> taskList = FXCollections.observableArrayList(taskService.getAllTask());
        tbData.setItems(taskList);
        tcProjectName.setCellFactory(new Callback<>() {
            @Override
            public TableCell<TaskDTO, String> call(TableColumn<TaskDTO, String> taskStringTableColumn) {
                return new TableCell<>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            ProjectNameDao projectNameDao = new ProjectNameDao();
                            ProjectName name = projectNameDao.get(item);
                            this.setStyle("-fx-background-color: #" + name.getProjectColor().substring(2) + ";");
                            setText(item);
                        }
                    }
                };
            }
        });
        tcNgPTr.setCellFactory(new Callback<TableColumn<TaskDTO, String>, TableCell<TaskDTO, String>>() {
            @Override
            public TableCell<TaskDTO, String> call(TableColumn<TaskDTO, String> taskStringTableColumn) {
                return new TableCell<>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            PersonDTO person = personService.getPersonByName(item);
                            this.setStyle("-fx-background-color: #" + person.getColor().substring(2) + ";");
                            setText(item);
                        }
                    }
                };
            }
        });
    }

    public void createDetailTask() {
        tbDetailTask.getItems().addAll();
        tbDetailTask.setEditable(true);
        tbDetailTask.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        LocalDate localDateNow = LocalDate.now();
        WeekFields weekFieldsf = WeekFields.of(DayOfWeek.MONDAY, 1);
        TemporalField weekOfYear = weekFieldsf.weekOfYear();
        int weekNumberNow = localDateNow.get(weekOfYear);
        for (int i = 0; i <= 51; i++) {
            TableColumn<TaskDTO, String> firstNameCol = new TableColumn<TaskDTO, String>("Tuần " + weekNumberNow);
            ArrayList<String> dateList = new ArrayList<>();
            dateList.add("Thứ hai");
            dateList.add("Thứ ba");
            dateList.add("Thứ tư");
            dateList.add("Thứ năm");
            dateList.add("Thứ sáu");
            for(int j=0;j<=4;j++){
                TableColumn<TaskDTO, String> subNameCol = new TableColumn<TaskDTO, String>(dateList.get(j));
                subNameCol.setCellFactory(new Callback<>() {
                    @Override
                    public TableCell<TaskDTO, String> call(TableColumn<TaskDTO, String> taskStringTableColumn) {
                        return new TableCell<>() {
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (!isEmpty()) {
                                    ProjectNameDao projectNameDao = new ProjectNameDao();
                                    System.out.println("test thu");
                                    ProjectName name = projectNameDao.get(item);
                                    this.setStyle("-fx-background-color: #" + name.getProjectColor().substring(2) + ";");
                                    setText(item);
                                }
                            }
                        };
                    }
                });
                firstNameCol.getColumns().add(subNameCol);
            }
            tbDetailTask.getColumns().add(firstNameCol);
            weekNumberNow++;
            weekNumberNow%=52;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createDetailTask();
        tbData.setEditable(true);
        tcProjectName.setEditable(true);
        tcProjectName.setCellValueFactory(new PropertyValueFactory<>("prName"));
        tcProjectName.setCellFactory(TextFieldTableCell.forTableColumn());
        tcTask.setEditable(true);
        tcTask.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcTask.setCellFactory(TextFieldTableCell.forTableColumn());
        tcNgPTr.setEditable(true);
        tcNgPTr.setCellValueFactory(new PropertyValueFactory<>("name"));

        tcDateStart.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tcDeadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        tcFinishDate.setCellValueFactory(new PropertyValueFactory<>("finishDate"));
        tcExpectedTime.setCellValueFactory(new PropertyValueFactory<>("expectedTime"));
        tcFinishTime.setCellValueFactory(new PropertyValueFactory<>("finishTime"));
        tcProcess.setEditable(true);
        tcProcess.setCellValueFactory(new PropertyValueFactory<>("processed"));
        tbData.setItems(listTask);
        tbData.setMaxWidth(940);
        refreshTable();


//        TableRow
    }

    //TODO:
    public void addTask(Event e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/AddTaskView.fxml"));
        Parent addTaskParent = loader.load();
        Scene scene = new Scene(addTaskParent);
        Stage addTaskWindow = new Stage();
        addTaskWindow.setTitle("Thêm công việc");
        addTaskWindow.setScene(scene);
        addTaskWindow.initModality(Modality.WINDOW_MODAL);
        addTaskWindow.initOwner(stage);
        addTaskWindow.showAndWait();
        refreshTable();
    }

    public void updateTask(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/UpdateTaskView.fxml"));
        Parent updateTaskParent = loader.load();
        Scene scene = new Scene(updateTaskParent);
        UpdateTaskViewController controller = loader.getController();
        TaskDTO selected = tbData.getSelectionModel().getSelectedItem();
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
            refreshTable();
        }
    }

    public void Delete(ActionEvent e) {
        TaskDTO selected = (TaskDTO) tbData.getSelectionModel().getSelectedItem();
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
                taskService.deleteTask(selected);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Thông báo");
                alert2.setHeaderText("Đã xóa");
                alert2.show();
                refreshTable();
            } else if (option.get() == ButtonType.CANCEL) {
            }
        }
    }

    public void btnProject(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/ProjectManagement.fxml"));
        Parent addTaskParent = loader.load();
        Scene scene = new Scene(addTaskParent);
        Stage addTaskWindow = new Stage();
        addTaskWindow.setTitle("Quản lý nhân sự");
        addTaskWindow.setScene(scene);
        addTaskWindow.initModality(Modality.WINDOW_MODAL);
        addTaskWindow.initOwner(stage);
        addTaskWindow.showAndWait();
        refreshTable();
    }

    public void btnPerson(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/PersonManagement.fxml"));
        Parent addTaskParent = loader.load();
        Scene scene = new Scene(addTaskParent);
        Stage addTaskWindow = new Stage();
        addTaskWindow.setTitle("Quản lý nhân sự");
        addTaskWindow.setScene(scene);
        addTaskWindow.initModality(Modality.WINDOW_MODAL);
        addTaskWindow.initOwner(stage);
        addTaskWindow.showAndWait();
        refreshTable();
    }

    public void Clicked(MouseEvent mouseEvent) throws IOException {
        TaskDTO selected = (TaskDTO) tbData.getSelectionModel().getSelectedItem();
        if (mouseEvent.getClickCount() == 2 && selected != null) {
            btnEdit.fire();
        }
    }
}
