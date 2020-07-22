package Controller.element;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class WeekTitle extends VBox {
    @FXML
    Label weekNum;
    @FXML
    RowDayTitle rowDayTitle;
    public WeekTitle(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/element/WeekTitle.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try{
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
