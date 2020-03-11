package NoteActiuni;

import NoteControllers.VizualizareNotaController;
import entities.Nota;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceNota;

public class VizualizareNotaApp extends Application {
    private Nota nota;
    //private ObservableList<Nota> modelNota;
    private ServiceNota serviceNota;

    public void setServiceNota(ServiceNota serviceNota) {
        this.serviceNota = serviceNota;
    }

//    public void setModelNota(ObservableList<Nota> modelNota) {
//        this.modelNota = modelNota;
//    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/vizualizareNota.fxml"));

        AnchorPane root=loader.load();

        VizualizareNotaController controller=loader.getController();
        controller.setNota(nota);
        //controller.setModelNota(modelNota);
        controller.setServiceNota(serviceNota);
        controller.setStage(primaryStage);

        Scene scene=new Scene(root);

        primaryStage.setTitle("PREVIZUALIZARE NOTA");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
