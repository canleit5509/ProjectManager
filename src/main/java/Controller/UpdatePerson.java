package Controller;

import DTO.PersonDTO;
import Service.PersonService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdatePerson implements Initializable {
    @FXML
    private Label txtID;
    @FXML
    private TextField txtName;
    @FXML
    private ColorPicker color;
    @FXML
    private RadioButton radioNow;
    @FXML
    private RadioButton radioRetired;
    private PersonService service;

    public UpdatePerson() {
        service = new PersonService();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group = new ToggleGroup();
        radioNow.setToggleGroup(group);
        radioRetired.setToggleGroup(group);
    }

    public void setPerson(PersonDTO person) {
        txtID.setText(person.getId());
        txtName.setText(person.getName());
        color.setValue(Color.valueOf(person.getColor()));
        if (person.getRetired() == 0) {
            radioRetired.setSelected(false);
            radioNow.setSelected(true);
        } else {
            radioNow.setSelected(false);
            radioRetired.setSelected(true);
        }
    }

    public void okBtn(ActionEvent e) {
        String id = txtID.getText();
        String name = txtName.getText();
        String txtColor = color.getValue().toString();
        int retired = 0;
        if (radioRetired.isSelected())
            retired = 1;

        PersonDTO person = new PersonDTO(id, name, txtColor, retired);
        service.updatePerson(person);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Cập nhật thành công");
        alert.show();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void cancelBtn(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }
}
