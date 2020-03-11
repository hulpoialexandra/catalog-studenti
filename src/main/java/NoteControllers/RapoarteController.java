package NoteControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class RapoarteController {
    private String mes;

    public void setMes(String mes) {
        this.mes=mes;
        txt.setText(mes);
        txt.setDisable(true);
    }

    @FXML
    private TextArea txt;

    @FXML
    public void initialize(){

    }
}
