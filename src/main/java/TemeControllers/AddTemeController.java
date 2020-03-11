package TemeControllers;

import TemeActiuni.MesajErrApp;
import entities.Tema;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceTema;
import validator.ValidationException;

public class AddTemeController {
    private ObservableList<Tema> model;
    private ServiceTema serviceTema;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtSw;

    @FXML
    private TextField txtDw;

    public void setModel(ObservableList<Tema> model) {
        this.model = model;
    }

    public void setServiceTema(ServiceTema serviceTema) {
        this.serviceTema = serviceTema;
    }

    @FXML
    private Button btn;

    @FXML
    public void initialize(){
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Integer id=Integer.parseInt(txtId.getText());
                String desc=txtDesc.getText();
                Integer sw=Integer.parseInt(txtSw.getText());
                Integer dw=Integer.parseInt(txtDw.getText());
                Tema t=new Tema(id,desc,sw,dw);
                try {
                    Tema t2=serviceTema.saveService(t);
                    if(t2!=null){
                        MesajErrApp mesajErrApp=new MesajErrApp();
                        mesajErrApp.setMsg("Tema existenta");
                        try {
                            mesajErrApp.start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                catch (ValidationException ex){
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
