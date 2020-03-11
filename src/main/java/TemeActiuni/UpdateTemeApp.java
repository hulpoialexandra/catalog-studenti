package TemeActiuni;

import TemeControllers.UpdateTemeController;
import entities.Tema;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceTema;

public class UpdateTemeApp extends Application {
    private ServiceTema serviceTema;
    private ObservableList<Tema> model;
    private int tid;

    public void setServiceTema(ServiceTema serviceTema) {
        this.serviceTema = serviceTema;
    }

    public void setModel(ObservableList<Tema> model) {
        this.model = model;
    }

    public void setT(int tid) {
        this.tid=tid;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/updateTeme.fxml"));

        AnchorPane root=loader.load();
        Scene scene=new Scene(root);

        UpdateTemeController controller=loader.getController();
        controller.setModel(model);
        controller.setServiceTema(serviceTema);
        System.out.println("App"+tid);
        controller.setT(tid);

        primaryStage.setScene(scene);
        primaryStage.setTitle("UPDATE TEMA");
        primaryStage.show();
    }


}
