package TemeActiuni;

import TemeControllers.AddTemeController;
import entities.Tema;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceTema;

public class AddTemeApp extends Application {
//    public static void main() {
//        launch();
//    }
    private ServiceTema serviceTema;
    private ObservableList<Tema> model;

    public void setModel(ObservableList<Tema> model) {
        this.model = model;
    }

    public void setServiceTema(ServiceTema serviceTema) {
        this.serviceTema = serviceTema;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/addTeme.fxml"));
        AnchorPane root=loader.load();
        Scene scene=new Scene(root);
        AddTemeController controller=loader.getController();
        controller.setServiceTema(serviceTema);
        controller.setModel(model);

        primaryStage.setTitle("ADAUGA TEMA");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
