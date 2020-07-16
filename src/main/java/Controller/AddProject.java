package Controller;

import Model.ProjectName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddProject {
    @FXML
    TextField prName;
    @FXML
    ColorPicker color;
    DatabaseConnector dbConn;
    ProjectNameDao nameDao = new ProjectNameDao();
    public void okBtn(ActionEvent e) {
        ProjectName projectName = new ProjectName(prName.getText(),color.getValue().toString());
        nameDao.add(projectName);
//        dbConn = new DatabaseConnector();
//        dbConn.addProject(projectName);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Thêm thành công");
        alert.show();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void cancelBtn(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }
}
