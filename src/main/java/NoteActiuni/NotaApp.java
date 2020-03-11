package NoteActiuni;

import NoteControllers.NotaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NotaApp extends Application {
    private String prof;

    public void setProf(String prof) {
        this.prof = prof;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/note.fxml"));

        AnchorPane root=loader.load();

        NotaController controller=loader.getController();
        controller.setProf(prof);

        Scene scene=new Scene(root);

        primaryStage.setTitle("NOTE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
