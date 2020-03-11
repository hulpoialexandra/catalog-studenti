package NoteActiuni;

import NoteControllers.RapoarteController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RapoarteApp extends Application {
    private String mes;

    public void setMes(String mes) {
        this.mes = mes;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/rapoarte.fxml"));

        AnchorPane root=loader.load();

        RapoarteController controller=loader.getController();
        controller.setMes(mes);

        Scene scene=new Scene(root);

        primaryStage.setTitle("RAPOARTE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
