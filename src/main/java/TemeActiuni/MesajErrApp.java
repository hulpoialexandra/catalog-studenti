package TemeActiuni;

import TemeControllers.MesajErrController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MesajErrApp extends Application {

    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/mesajErr.fxml"));

        AnchorPane root=loader.load();

        Scene scene=new Scene(root);

        MesajErrController controller=loader.getController();
        controller.setMsg(msg);

        primaryStage.setScene(scene);
        primaryStage.setTitle("ERROR");
        primaryStage.show();
    }
}
