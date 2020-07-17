package Controller;

import DAO.ProjectNameDao;
import Model.ProjectName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateProject implements Initializable {
    @FXML
    TextField txtName;
    @FXML
    ColorPicker color;
    @FXML
    RadioButton radioNow;
    @FXML
    RadioButton radioDone;
    String oldName="";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group = new ToggleGroup();
        radioNow.setToggleGroup(group);
        radioDone.setToggleGroup(group);
    }
    public void setProject(ProjectName projectName){
        oldName = txtName.getText();
        txtName.setText(projectName.getProjectName());
        color.setValue(Color.valueOf(projectName.getProjectColor()));
        if(projectName.getDone()==0){
            radioDone.setSelected(false);
            radioNow.setSelected(true);
        }else{
            radioNow.setSelected(false);
            radioDone.setSelected(true);
        }
    }

    public void okBtn(ActionEvent e) {
        String name = txtName.getText();
        String txtColor = color.getValue().toString();
        int done=0;
        if(radioDone.isSelected())
            done=1;
        ProjectName project = new ProjectName(name,txtColor,done);
        ProjectNameDao projectNameDao = new ProjectNameDao();
        projectNameDao.update(project,oldName);
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
