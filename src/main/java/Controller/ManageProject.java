package Controller;

import DAO.ProjectNameDao;
import Model.Person;
import Model.ProjectName;
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

public class ManageProject implements Initializable {
    @FXML
    TableView tbData;
    @FXML
    RadioButton checkNow;
    @FXML
    RadioButton checkDone;
    @FXML
    RadioButton checkAll;
    @FXML
    TableColumn<ProjectName, String> tcName;
    @FXML
    Button btnDone;
    ProjectNameDao projectNameDao = new ProjectNameDao();

    public void btnAdd(ActionEvent e) throws IOException {
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
        RefreshTable(projectNameDao.getAllNow());
        checkNow.setSelected(true);
    }

    public void btnUpdate(ActionEvent e) throws IOException {
        ProjectName projectName = (ProjectName) tbData.getSelectionModel().getSelectedItem();
        if (projectName == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Vui lòng chọn dự án");
            alert.show();
        } else {
            Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/UpdateProject.fxml"));
            Parent updateProject = loader.load();
            Scene scene = new Scene(updateProject);
            Stage updateProjectWindow = new Stage();
            updateProjectWindow.setTitle("Cập nhật nhân sự");
            updateProjectWindow.setScene(scene);
            updateProjectWindow.initModality(Modality.WINDOW_MODAL);
            updateProjectWindow.initOwner(stage);
            UpdateProject updateProject1 = loader.getController();
            updateProject1.setProject(projectName);
            updateProject1.oldName=projectName.getProjectName();
            updateProjectWindow.showAndWait();
            RefreshTable(projectNameDao.getAllNow());
            checkNow.setSelected(true);
            btnDone.setVisible(true);
        }
    }

    public void btnKick(ActionEvent actionEvent) {
        ProjectName projectName = (ProjectName) tbData.getSelectionModel().getSelectedItem();
        if (projectName == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Vui lòng chọn dự án");
            alert.show();
        } else {
            projectName.setDone(1);
            projectNameDao.update(projectName,projectName.getProjectName());
            RefreshTable(projectNameDao.getAllNow());
        }
    }
    public void refreshColor(){
        tcName.setCellFactory(new Callback<>() {
            @Override
            public TableCell<ProjectName, String> call(TableColumn<ProjectName, String> taskStringTableColumn) {
                return new TableCell<>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            ProjectName projectName = projectNameDao.get(item);
                            this.setStyle("-fx-background-color: #" + projectName.getProjectColor().substring(2) + ";");
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
        checkDone.setToggleGroup(group);
        checkAll.setToggleGroup(group);
        checkNow.setSelected(true);
        ObservableList<ProjectName> personList = FXCollections.observableArrayList(projectNameDao.getAllNow());
        tcName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        tbData.setItems(personList);
        refreshColor();
    }
    public void RefreshTable(ArrayList<ProjectName> list){
        ObservableList<ProjectName> projectList = FXCollections.observableArrayList(list);
        tbData.setItems(projectList);
    }

    public void checkNow(ActionEvent actionEvent) {
        RefreshTable(projectNameDao.getAllNow());
        btnDone.setVisible(true);
        refreshColor();
    }

    public void checkRetire(ActionEvent actionEvent) {
        RefreshTable(projectNameDao.getAllDone());
        btnDone.setVisible(false);
        refreshColor();
    }

    public void checkAll(ActionEvent actionEvent) {
        RefreshTable(projectNameDao.getAll());
        btnDone.setVisible(false);
        refreshColor();
    }
}
