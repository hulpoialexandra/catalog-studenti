package NoteControllers;

import TemeActiuni.MesajErrApp;
import entities.Nota;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceNota;
import validator.ValidationException;

public class VizualizareNotaController {
    private Nota nota;
//    private ObservableList<Nota> modelNota;
    private ServiceNota serviceNota;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setServiceNota(ServiceNota serviceNota) {
        this.serviceNota = serviceNota;
    }

//    public void setModelNota(ObservableList<Nota> modelNota) {
//        this.modelNota = modelNota;
//    }



    public void setNota(Nota nota) {
        this.nota=nota;
        tema.setText(nota.getTema().toString());
        tema.setDisable(true);
        stud.setText(nota.getNume()+nota.getPrenume());
        stud.setDisable(true);
        not.setText(nota.getNota().toString());
        not.setDisable(true);
        data.setText(nota.getData().toString());
        data.setDisable(true);
        fb.setText(nota.getFeedback());
        fb.setDisable(true);
    }

    @FXML
    private TextField tema;

    @FXML
    private TextField stud;

    @FXML
    private TextField not;

    @FXML
    private TextField data;

    @FXML
    private TextField fb;

    @FXML
    private Button btn;

    @FXML
    public void initialize(){
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Nota t2=serviceNota.saveService(nota);
                    if(t2!=null){
                        MesajErrApp mesajErrApp=new MesajErrApp();
                        mesajErrApp.setMsg("Nota existenta");
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
                //modelNota.setAll((List<Nota>)serviceNota.findAllService());
                stage.close();
            }
        });
    }
}
