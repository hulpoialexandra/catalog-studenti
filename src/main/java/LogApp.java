import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LogApp extends Application {
    public static void  main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/log.fxml"));

        AnchorPane root=loader.load();

        LogController controller=loader.getController();
        controller.setStage(primaryStage);

        Scene scene=new Scene(root);

        primaryStage.setTitle("LOGIN");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
