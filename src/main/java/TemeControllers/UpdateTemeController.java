package TemeControllers;

import TemeActiuni.MesajErrApp;
import entities.Tema;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceTema;
import validator.ValidationException;

public class UpdateTemeController {
    private ServiceTema serviceTema;
    private ObservableList<Tema> model;
    private Integer tid;

    public void setServiceTema(ServiceTema serviceTema) {
        this.serviceTema = serviceTema;
    }

    public void setModel(ObservableList<Tema> model) {
        this.model = model;
    }

    public void setT(Integer tid) {
        this.tid = tid;
        txtId.setText(tid.toString());
        txtId.setDisable(true);
    }

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtSw;

    @FXML
    private TextField txtDw;

    @FXML
    private Button btn;

    @FXML
    private AnchorPane updAP;

    @FXML
    public void initialize(){
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String desc=txtDesc.getText();
                Integer sw=Integer.parseInt(txtSw.getText());
                Integer dw=Integer.parseInt(txtDw.getText());
                Tema t=new Tema(tid,desc,sw,dw);
                try {
                    serviceTema.updateService(t);
                }catch (ValidationException ex){
                        MesajErrApp mesajErrApp=new MesajErrApp();
                        mesajErrApp.setMsg(ex.getMesaj());
                        try {
                            mesajErrApp.start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
                model.setAll(serviceTema.getAll());
            }
        });
    }
}
