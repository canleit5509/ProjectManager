package Controller;


import Model.Task;
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

    }
    public Task getTask(){
        Task task = new Task();
        task.setId(id.getText());
        task.setPrName(prName.getPromptText());
        task.setName(name.getPromptText());
        task.setTitle(title.getText());
        task.setStartDate(startDate.getPromptText());
        task.setDeadline(deadline.getPromptText());
        task.setFinishDate(finishDate.getPromptText());
        task.setExpectedTime(Integer.parseInt(expectedTime.getText()));
        task.setFinishTime(Integer.parseInt(finishTime.getText()));
        task.setProcessed(Integer.parseInt(processed.getText()));
        return task;
    }
    public void setComboBox(){

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
        addProjectWindow.showAndWait();
        setComboBox();
        AddPerson addPerson = loader.getController();
        addPerson.setID();
    }
    public void cancel(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }
}
