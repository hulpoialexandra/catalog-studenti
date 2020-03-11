package TemeControllers;

import TemeActiuni.AddTemeApp;
import TemeActiuni.UpdateTemeApp;
import entities.Tema;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ServiceTema;

import java.util.stream.Collectors;


public class TemeController{

    @FXML
    private TableColumn<Tema,String> id;

    @FXML
    private TableColumn<Tema,String> descriere;

    @FXML
    private TableColumn<Tema,String> startWeek;

    @FXML
    private TableColumn<Tema,String> deadlineWeek;

    @FXML
    private TableView<Tema> tableViewTeme;

    @FXML
    private TextField nrTema;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpd;

    @FXML
    private Button btnDel;

    private ObservableList<Tema> model= FXCollections.observableArrayList();
    private ServiceTema serviceTema;

    public void setService(ServiceTema service){
        this.serviceTema=service;
        model.setAll(serviceTema.getAll());

    }

//    public TemeController(ServiceTema serviceTema) {
//        this.serviceTema = serviceTema;
//        model.setAll(serviceTema.getAll());
//    }

    @FXML
    public void initialize(){
        tableViewTeme.setItems(model);
        id.setCellValueFactory(new PropertyValueFactory<Tema,String>("id"));
        descriere.setCellValueFactory(new PropertyValueFactory<Tema,String>("descriere"));
        startWeek.setCellValueFactory(new PropertyValueFactory<Tema,String>("startWeek"));
        deadlineWeek.setCellValueFactory(new PropertyValueFactory<Tema,String>("deadlineWeek"));

//        nrTema.textProperty().addListener((observable,oldValue,newValue)->{
//            model.setAll(serviceTema.getAll().stream()
//                .filter(x->x.getId().toString().startsWith(nrTema.getText()))
//                    .collect(Collectors.toList())
//            );
//        });

        nrTema.textProperty().addListener((observable,oldValue,newValue)->{
            model.setAll(serviceTema.getAll().stream()
                    .filter(x->x.getDescriere().startsWith(nrTema.getText())||x.getId().toString().startsWith(nrTema.getText()))
                    .collect(Collectors.toList())
            );
        });


        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddTemeApp addTemeApp=new AddTemeApp();
                addTemeApp.setServiceTema(serviceTema);
                addTemeApp.setModel(model);
                try {
                    addTemeApp.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnDel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Tema t=tableViewTeme.getSelectionModel().getSelectedItem();
                serviceTema.deleteService(t.getId());
                model.setAll(serviceTema.getAll());
            }
        });

        btnUpd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Integer t=tableViewTeme.getSelectionModel().getSelectedItem().getId();
                System.out.println(t);
                UpdateTemeApp updateTemeApp=new UpdateTemeApp();
                updateTemeApp.setModel(model);
                updateTemeApp.setServiceTema(serviceTema);
                updateTemeApp.setT(t);
                try {
                    updateTemeApp.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
