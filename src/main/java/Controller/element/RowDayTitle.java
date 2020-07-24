package Controller.element;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RowDayTitle extends HBox implements Initializable {
    @FXML
    Label mon;
    @FXML
    Label tue;
    @FXML
    Label wed;
    @FXML
    Label thu;
    @FXML
    Label fri;

    public RowDayTitle() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Element/RowDayTitle.fxml"));
        loader.setRoot(this);
        try {
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
