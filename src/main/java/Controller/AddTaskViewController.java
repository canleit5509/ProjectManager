package Controller;


import DTO.TaskDTO;
import Service.PersonService;
import Service.ProjectNameService;
import Service.TaskService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

public class AddTaskViewController implements Initializable {
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
    Label expectedTime;
    @FXML
    Label finishTime;
    @FXML
    Label processed;
    private PersonService personService;
    private ProjectNameService projectNameService;
    private TaskService taskService;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taskService = new TaskService();
        personService = new PersonService();
        projectNameService = new ProjectNameService();
        setComboBox();
        Random random = new Random();
        int taskID = random.nextInt(899999) + 100000;
        id.setText(String.valueOf(taskID));
        processed.setText("0%");
    }

    public boolean validate() {
        if (prName.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn dự án!");
            alert.showAndWait();
            return false;
        }
        if (name.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn nhân sự");
            alert.showAndWait();
            return false;
        }
        if (title.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn tên công việc");
            alert.showAndWait();
            return false;
        }
        if (startDate.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn ngày bắt đầu");
            alert.showAndWait();
            return false;
        }
        if (deadline.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn ngày deadline");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public TaskDTO getTask() {
        TaskDTO task = new TaskDTO();
        task.setId(id.getText());
        task.setPrName(prName.getValue());
        if (!name.getValue().contains("|"))
            task.setName(name.getValue());
        else
            task.setName(name.getValue().substring(7));
        task.setTitle(title.getText());
        task.setStartDate(String.valueOf(startDate.getValue()));
        task.setDeadline(String.valueOf(deadline.getValue()));
        if (!String.valueOf(finishDate.getValue()).equals("null")) {
            task.setFinishDate(String.valueOf(finishDate.getValue()));
        }
        task.setExpectedTime(Integer.parseInt(expectedTime.getText()));
        if (!finishTime.getText().equals("")) {
            task.setFinishTime(Integer.parseInt(finishTime.getText()));
        }
        if (!processed.getText().equals("")) {
            task.setProcessed(Integer.parseInt(processed.getText()));
        }
        return task;
    }

    public void setComboBox() {
        ObservableList<String> prList = FXCollections.observableArrayList(projectNameService.getAllDoingProjectName());
        ObservableList<String> personList = FXCollections.observableArrayList(personService.getDoingPeopleIdName());
        prName.setItems(prList);
        name.setItems(personList);
    }

    public int workDays(LocalDate date1, LocalDate date2) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date11 = df.parse(String.valueOf(date1));
        Date date22 = df.parse(String.valueOf(date2));
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date11);
        cal2.setTime(date22);
        cal2.add(Calendar.DATE, 1);
        int numberOfDays = 0;
        while (cal1.before(cal2)) {
            if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
                    && (Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                numberOfDays++;
            }
            cal1.add(Calendar.DATE, 1);
        }
        return numberOfDays;
    }

    public void onPickStart(ActionEvent e) {
        Callback<DatePicker, DateCell> callback = new Callback<>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                        setDisable(empty || item.compareTo(startDate.getValue()) < 0);
                    }

                };
            }
        };
        deadline.setDayCellFactory(callback);
        finishDate.setDayCellFactory(callback);
    }

    public void onPickDeadline(ActionEvent e) throws ParseException {
        LocalDate date1 = startDate.getValue();
        LocalDate date2 = deadline.getValue();
        if (date2.compareTo(date1) < 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Ngày deadline trước ngày bắt đầu!");
            alert.showAndWait();
        } else {
            expectedTime.setText(String.valueOf(workDays(date1, date2)));
        }
    }

    public void onPickFinishDate(ActionEvent e) throws ParseException {
        LocalDate date1 = startDate.getValue();
        LocalDate date2 = finishDate.getValue();
        if (date2.compareTo(date1) < 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Ngày hoàn thành trước ngày bắt đầu!");
            alert.showAndWait();
        } else {
            finishTime.setText(String.valueOf(workDays(date1, date2)));
        }
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
        addProjectWindow.showAndWait();
        setComboBox();
        AddPerson addPerson = loader.getController();
        addPerson.setID();
    }

    public void cancel(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void submit(ActionEvent e) {
        if (validate()) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            taskService.addTask(getTask());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Thêm thành công");
            alert.showAndWait();
            stage.close();
        }
    }
}
