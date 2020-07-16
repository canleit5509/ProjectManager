package Controller;


import Model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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

    public void setTask(Task task){
        //TODO: chinh database task.startDate, deadline, finishDate thanh Date
        id.setText(task.getId());
        prName.setValue(task.getPrName());
        name.setValue(task.getName());
        title.setText(task.getTitle());
        startDate.setValue(LocalDate.parse(task.getStartDate()));
        deadline.setValue(LocalDate.parse(task.getDeadline()));
        finishDate.setValue(LocalDate.parse(task.getFinishDate()));
        expectedTime.setText(task.getExpectedTime()+"");
        finishTime.setText(task.getFinishTime()+"");
        processed.setText(task.getProcessed()+"");
    }

    public void setComboBox(){
        PersonDao personDao = new PersonDao();
        ProjectNameDao projectNameDao = new ProjectNameDao();
        ObservableList<String> prList = FXCollections.observableArrayList(projectNameDao.getAllName());
        ObservableList<String> personList = FXCollections.observableArrayList(personDao.getAllName());
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

    public void btnOK(ActionEvent e) throws IOException {
        String txtID = id.getText();
        String txtPrName = prName.getValue();
        String txtName="";
        if(!name.getValue().contains("|"))
            txtName = name.getValue();
        else
            txtName = name.getValue().substring(7);
        String txtTitle = title.getText();
        String txtStartDate = startDate.getValue().toString();
        String txtDeadline = deadline.getValue().toString();
        String txtFinishDate = finishDate.getValue().toString();
        int intExpectTime = Integer.parseInt(expectedTime.getText());
        int intFinishTime = Integer.parseInt(finishTime.getText());
        int intProcessed = Integer.parseInt(processed.getText());
        TaskDao taskDao = new TaskDao();
        Task task = new Task(txtID,txtPrName,txtTitle,txtName,txtStartDate,txtDeadline,txtFinishDate
                ,intExpectTime,intFinishTime,intProcessed);
        taskDao.update(task);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Cập nhật thành công");
        alert.showAndWait();
//        FXMLLoader loader = new FXMLLoader();
//        PrimaryViewController primaryView = loader.getController();
//        ObservableList<Task> taskList = FXCollections.observableArrayList(taskDao.getAll());
//        primaryView.tbData.setItems(taskList);
//        primaryView.RefreshTable();
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }
}
