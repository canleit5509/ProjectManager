package Controller.element;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class RowDayTitle extends HBox {
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
    public RowDayTitle(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Element/RowDayTitle.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try{
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
