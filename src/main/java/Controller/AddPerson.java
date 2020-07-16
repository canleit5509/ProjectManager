package Controller;

import Model.Person;
import Model.ProjectName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class AddPerson implements Initializable {
    @FXML
    Label txtID;
    @FXML
    TextField psName;
    @FXML
    ColorPicker color;
    PersonDao personDao = new PersonDao();
    public void setID(){
        Random random = new Random();
        int id = random.nextInt(899999)+100000;
        txtID.setText(id+"");
    }
    public void okBtn(ActionEvent e) {
        Person person = new Person(txtID.getText(),psName.getText(),color.getValue().toString());
        personDao.add(person);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setID();
    }
}
