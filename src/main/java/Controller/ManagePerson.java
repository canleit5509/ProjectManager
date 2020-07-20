package Controller;

import DAO.PersonDao;
import Model.Person;
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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManagePerson implements Initializable {
    @FXML
    TableView tbData;
    @FXML
    RadioButton checkNow;
    @FXML
    RadioButton checkRetire;
    @FXML
    RadioButton checkAll;
    @FXML
    TableColumn<Person, String> tcID;
    @FXML
    TableColumn<Person, String> tcName;
    @FXML
    Button btnKick;
    PersonDao personDao = new PersonDao();

    public void btnAdd(ActionEvent e) throws IOException {
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
        RefreshTable(personDao.getAllPersonNow());
        refreshColor();
    }

    public void btnUpdate(ActionEvent e) throws IOException {
        Person person = (Person) tbData.getSelectionModel().getSelectedItem();
        if (person == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Vui lòng chọn nhân viên");
            alert.show();
        } else {
            Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
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
            RefreshTable(personDao.getAllPersonNow());
            refreshColor();
            checkNow.setSelected(true);
            btnKick.setVisible(true);
        }

    }

    public void btnKick(ActionEvent actionEvent) {
        Person person = (Person) tbData.getSelectionModel().getSelectedItem();
        if (person == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Vui lòng chọn nhân viên");
            alert.show();
        } else {
            person.setRetired(1);
            personDao.update(person);
            RefreshTable(personDao.getAllPersonNow());
            refreshColor();
        }
    }

    public void refreshColor(){
        tcID.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Person, String> call(TableColumn<Person, String> taskStringTableColumn) {
                return new TableCell<>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            Person person = personDao.getByID(item);
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
        PersonDao personDao = new PersonDao();
        ObservableList<Person> personList = FXCollections.observableArrayList(personDao.getAllPersonNow());
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbData.setItems(personList);
        refreshColor();
    }
    public void RefreshTable(ArrayList<Person> list){
        ObservableList<Person> personList = FXCollections.observableArrayList(list);
        tbData.setItems(personList);
    }

    public void checkNow(ActionEvent actionEvent) {
        RefreshTable(personDao.getAllPersonNow());
        btnKick.setVisible(true);
        refreshColor();
    }

    public void checkRetire(ActionEvent actionEvent) {
        RefreshTable(personDao.getAllPersonRetired());
        btnKick.setVisible(false);
        refreshColor();
    }

    public void checkAll(ActionEvent actionEvent) {
        RefreshTable(personDao.getAll());
        btnKick.setVisible(false);
        refreshColor();
    }
}
