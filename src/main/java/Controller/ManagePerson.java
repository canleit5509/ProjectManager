package Controller;

import DTO.PersonDTO;
import Model.Person;
import Service.PersonService;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManagePerson implements Initializable {
    @FXML
    private TableView tbData;
    @FXML
    private RadioButton checkNow;
    @FXML
    private RadioButton checkRetire;
    @FXML
    private RadioButton checkAll;
    @FXML
    private TableColumn<Person, String> tcID;
    @FXML
    private TableColumn<Person, String> tcName;
    @FXML
    private Button btnKick;
    private PersonService service;

    public ManagePerson() {
        service = new PersonService();
    }

    public void btnAdd(ActionEvent e) throws IOException {
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
        RefreshTable(service.getRetiredPeople(0));
    }

    public void btnUpdate(ActionEvent e) throws IOException {
        PersonDTO person = (PersonDTO) tbData.getSelectionModel().getSelectedItem();
        if (person == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Vui lòng chọn nhân viên");
            alert.show();
        } else {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/UpdatePerson.fxml"));
            Parent addProject = loader.load();
            Scene scene = new Scene(addProject);
            Stage addProjectWindow = new Stage();
            addProjectWindow.setTitle("Cập nhật nhân sự");
            addProjectWindow.setScene(scene);
            addProjectWindow.initModality(Modality.WINDOW_MODAL);
            addProjectWindow.initOwner(stage);
            UpdatePerson updatePerson = loader.getController();
            updatePerson.setPerson(person);
            addProjectWindow.showAndWait();
            RefreshTable(service.getRetiredPeople(0));
            checkNow.setSelected(true);
            btnKick.setVisible(true);
        }

    }

    public void btnKick(ActionEvent actionEvent) {
        PersonDTO person = (PersonDTO) tbData.getSelectionModel().getSelectedItem();
        if (person == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Vui lòng chọn nhân viên");
            alert.show();
        } else {
            person.setRetired(1);
            service.updatePerson(person);
            RefreshTable(service.getRetiredPeople(0));
        }
    }

    public void refreshColor() {
        tcID.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Person, String> call(TableColumn<Person, String> taskStringTableColumn) {
                return new TableCell<>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            PersonDTO person = service.getPersonByID(item);
                            this.setStyle("-fx-background-color: #" + person.getColor().substring(2) + ";");
                            setText(item);
                        }
                    }
                };
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group = new ToggleGroup();
        checkNow.setToggleGroup(group);
        checkRetire.setToggleGroup(group);
        checkAll.setToggleGroup(group);
        checkNow.setSelected(true);
        ObservableList<PersonDTO> personList = FXCollections.observableArrayList(service.getRetiredPeople(0));
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbData.setItems(personList);
        refreshColor();
    }

    public void RefreshTable(ArrayList<PersonDTO> list) {
        ObservableList<PersonDTO> personList = FXCollections.observableArrayList(list);
        tbData.setItems(personList);
    }

    public void checkNow(ActionEvent actionEvent) {
        RefreshTable(service.getRetiredPeople(0));
        btnKick.setVisible(true);
        refreshColor();
    }

    public void checkRetire(ActionEvent actionEvent) {
        RefreshTable(service.getRetiredPeople(1));
        btnKick.setVisible(false);
        refreshColor();
    }

    public void checkAll(ActionEvent actionEvent) {
        RefreshTable(service.getAllPeople());
        btnKick.setVisible(false);
        refreshColor();
    }
}
