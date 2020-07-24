package Controller.element;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WeekTitle extends VBox implements Initializable {
    @FXML
    Label weekNum;
    @FXML
    RowDayTitle rowDayTitle;
    public WeekTitle(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Element/WeekTitle.fxml"));
        loader.setRoot(this);
        try{
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public Node getStyleableNode() {
        return null;
    }
}
