package Controller;


import Model.Task;
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


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
    TextField expectedTime;
    @FXML
    TextField finishTime;
    @FXML
    TextField processed;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setComboBox();
        Random random = new Random();
        int taskID = random.nextInt(899999) + 100000;
        id.setText(String.valueOf(taskID));

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

    public void setComboBox() {
        PersonDao personDao = new PersonDao();
        ProjectNameDao projectNameDao = new ProjectNameDao();
        ObservableList<String> prList = FXCollections.observableArrayList(projectNameDao.getAllName());
        ObservableList<String> personList = FXCollections.observableArrayList(personDao.getAllName());
        prName.setItems(prList);
        name.setItems(personList);
    }

    public int workDays(LocalDate begin, LocalDate end) {
        int beginW = begin.getDayOfWeek().getValue();
        int endW = end.getDayOfWeek().getValue();
        int days = end.compareTo(begin);
        int result = days - 2 * (days / 7);
        if (!(days % 7 == 0)) {
            if (beginW == 7) {
                result -= 1;
            } else if (endW == 7) {
                result -= 1;
            } else if (endW < beginW) {
                result -= 2;
            }
        }
        return result;
    }

    public void onPickDeadline(ActionEvent e) {
        LocalDate ldStart = startDate.getValue();
        LocalDate ldDeadline = deadline.getValue();
        if (ldDeadline.compareTo(ldStart) < 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Ngày deadline trước ngày bắt đầu!");
            alert.showAndWait();
        } else {
            expectedTime.setText(String.valueOf(workDays(ldStart,ldDeadline)));
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

    public void cancel(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void submit(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        System.out.println(getTask());
        TaskDao taskDao = new TaskDao();
        taskDao.add(getTask());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Thêm thành công");
        alert.showAndWait();
        stage.close();
    }
}
