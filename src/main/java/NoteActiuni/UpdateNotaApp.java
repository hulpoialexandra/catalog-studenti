package NoteActiuni;

import NoteControllers.UpdateNotaController;
import NoteControllers.VizualizareNotaController;
import entities.Nota;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceNota;

import java.util.Map;

public class UpdateNotaApp extends Application {
   // private ObservableList<Nota> modelNota;
    private ServiceNota serviceNota;
    //private Nota nota;

//    public void setNota(Nota nota) {
//        this.nota = nota;
//    }

//    public void setModelNota(ObservableList<Nota> modelNota) {
//        this.modelNota = modelNota;
//    }

    public void setServiceNota(ServiceNota serviceNota) {
        this.serviceNota = serviceNota;
    }

    public void setId(Map id) {
        this.id = id;
    }

    private Map id;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/updateNota.fxml"));

        AnchorPane root=loader.load();

        UpdateNotaController controller=loader.getController();
        //controller.setModelNota(modelNota);
        controller.setServiceNota(serviceNota);
        controller.setId(id);
        controller.setStage(primaryStage);
        //controller.setNota(nota);

        Scene scene=new Scene(root);

        primaryStage.setTitle("UPDATE NOTA");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
