package Controller;


import DAO.PersonDao;
import DAO.ProjectNameDao;
import DAO.TaskDao;
import Model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    public void setTask(Task task) {
        //TODO: chinh database task.startDate, deadline, finishDate thanh Date
        id.setText(task.getId());
        prName.setValue(task.getPrName());
        name.setValue(task.getName());
        title.setText(task.getTitle());
        startDate.setValue(LocalDate.parse(task.getStartDate()));
        deadline.setValue(LocalDate.parse(task.getDeadline()));
        if (task.getFinishDate() == null) {
            finishDate.setValue(null);
        } else {
            finishDate.setValue(LocalDate.parse(task.getFinishDate()));
        }
        expectedTime.setText(task.getExpectedTime() + "");
        finishTime.setText(task.getFinishTime() + "");
        processed.setText(task.getProcessed() + "");
    }

    public void setComboBox() {
        PersonDao personDao = new PersonDao();
        ProjectNameDao projectNameDao = new ProjectNameDao();
        ObservableList<String> prList = FXCollections.observableArrayList(projectNameDao.getAllNameNow());
        ObservableList<String> personList = FXCollections.observableArrayList(personDao.getAllIDName());
        prName.setItems(prList);
        name.setItems(personList);
    }

    public void cancel(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void AddProject(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
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
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
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

    public Task getTask() {
        Task task = new Task();
        task.setId(id.getText());
        task.setPrName(prName.getValue());

        if (!name.getValue().contains("|"))
            task.setName(name.getValue());
        else
            task.setName(name.getValue().substring(7));
        task.setTitle(title.getText());

        String txtStartDate = String.valueOf(startDate.getValue());
        if (!txtStartDate.equals("null")) {
            task.setStartDate(txtStartDate);
        }
        String txtDeadline = String.valueOf(deadline.getValue());
        if (!txtDeadline.equals("null")) {
            task.setDeadline(txtDeadline);
        }
        if (!String.valueOf(finishDate.getValue()).equals("null")) {
            task.setFinishDate(String.valueOf(finishDate.getValue()));
        }
        if (!expectedTime.getText().isBlank()) {
            task.setExpectedTime(Integer.parseInt(expectedTime.getText()));
        }
        if (!finishTime.getText().isBlank()) {
            task.setFinishTime(Integer.parseInt(finishTime.getText()));
        }
        if (!processed.getText().isBlank()) {
            task.setProcessed(Integer.parseInt(processed.getText()));
        }
        return task;
    }

    public void btnOK(ActionEvent e) {
        TaskDao taskDao = new TaskDao();
        Task task = getTask();
        taskDao.update(task);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Cập nhật thành công");
        alert.showAndWait();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }
}
