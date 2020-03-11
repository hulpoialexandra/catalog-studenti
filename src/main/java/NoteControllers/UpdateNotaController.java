package NoteControllers;

import TemeActiuni.MesajErrApp;
import entities.Nota;
import entities.Student;
import entities.Tema;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceNota;
import validator.ValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class UpdateNotaController {
    //private ObservableList<Nota> modelNota;
    private ServiceNota serviceNota;
    private Stage stage;
//    private Nota nota;

//    public void setNota(Nota nota) {
//        this.nota = nota;
//    }

    public void setId(Map id) {
        this.id = id;
        Tema tem=(Tema) id.values().toArray()[0];
        Student student=(Student)id.keySet().toArray()[0];
        tema.setText(tem.getId().toString());
        tema.setDisable(true);
        stud.setText(student.getNume()+" "+student.getPrenume());
        stud.setDisable(true);
    }

    private Map id;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setServiceNota(ServiceNota serviceNota) {
        this.serviceNota = serviceNota;
    }

//    public void setModelNota(ObservableList<Nota> modelNota) {
//        this.modelNota = modelNota;
//    }


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
                if(fb.getText().equals("")){
                    fb.setText("Nicio observatie");
                }
                if(data.getText().equals("")){
                    data.setText(LocalDate.now().toString());
                }
                if(not.getText().equals("")){
                    MesajErrApp mesajErrApp=new MesajErrApp();
                    mesajErrApp.setMsg("Nota vida");
                    try {
                        mesajErrApp.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    try{
                        LocalDate.parse(data.getText(),dtf);
                    }
                    catch(Exception e){
                        MesajErrApp mesajErrApp=new MesajErrApp();
                        mesajErrApp.setMsg("Format invalid");
                        try {
                            mesajErrApp.start(new Stage());
                        } catch (Exception ex) {
                            e.printStackTrace();
                        }
                    }
                    Nota nota=serviceNota.findOneService(id);
                    Nota n=new Nota(id, LocalDate.parse(data.getText()),nota.getProfesor(),Integer.parseInt(not.getText()),fb.getText());
                    try {
                        serviceNota.updateService(n);
                        //modelNota.setAll((List<Nota>)serviceNota.findAllService());
                        stage.close();
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
                }
            }
        });
    }

}
