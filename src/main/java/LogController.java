import NoteActiuni.NotaApp;
import TemeActiuni.MainApp;
import TemeActiuni.MesajErrApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LogController {
    Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Label labelt;

    @FXML
    private TextField text;

    @FXML
    private Button btn;

    @FXML
    private Button teme;

    @FXML
    private Button note;

    @FXML
    private void initialize(){
        teme.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                labelt.setVisible(false);
                text.setVisible(false);
                btn.setVisible(false);
                MainApp temeapp=new MainApp();
                try {
                    temeapp.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        note.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //teme.setVisible(false);
                labelt.setVisible(true);
                text.setVisible(true);
                btn.setVisible(true);
            }
        });
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(text.getText().equals("")){
                    MesajErrApp mesajErrApp=new MesajErrApp();
                    mesajErrApp.setMsg("Nume vid");
                    try {
                        mesajErrApp.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    NotaApp notaApp=new NotaApp();
                    notaApp.setProf(text.getText());
                    try {
                        notaApp.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
