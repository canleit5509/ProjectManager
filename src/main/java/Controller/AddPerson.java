package Controller;

import DAO.PersonDao;
import DTO.PersonDTO;
import Model.Person;
import Service.PersonService;
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
    private Label txtID;
    @FXML
    private TextField psName;
    @FXML
    private ColorPicker color;
    PersonService service;
    public void setID(){
        Random random = new Random();
        int id = random.nextInt(899999)+100000;
        txtID.setText(id+"");
        service = new PersonService();
    }
    public void okBtn(ActionEvent e) {
        PersonDTO person = new PersonDTO(txtID.getText(),psName.getText(),color.getValue().toString(),0);
        service.addPerson(person);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Thêm thành công");
        alert.show();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        System.out.println("OK");
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
