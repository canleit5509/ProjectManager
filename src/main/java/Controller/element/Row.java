package Controller.element;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class Row extends HBox {
    @FXML
    Rectangle mon;
    @FXML
    Rectangle tue;
    @FXML
    Rectangle wed;
    @FXML
    Rectangle thu;
    @FXML
     Rectangle fri;

    public Row(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Element/Row.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try{
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
