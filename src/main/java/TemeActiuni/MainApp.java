package TemeActiuni;

import TemeControllers.TemeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceTema;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/scene.fxml"));
        AnchorPane root=loader.load();
        Scene scene=new Scene(root);
        ServiceTema srv=new ServiceTema();
        TemeController controller=loader.getController();
        controller.setService(srv);


        primaryStage.setTitle("TEME");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
