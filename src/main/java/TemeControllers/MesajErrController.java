package TemeControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.awt.*;

public class MesajErrController {
    private String msg;

    @FXML
    private TextField txt;

    public void setMsg(String msg) {
        this.msg = msg;
        txt.setText(msg);
        txt.setDisable(true);
    }

    @FXML
    public void initialize(){

    }
}
