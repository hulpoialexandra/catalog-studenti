package NoteControllers;

import NoteActiuni.RapoarteApp;
import NoteActiuni.UpdateNotaApp;
import NoteActiuni.VizualizareNotaApp;
import TemeActiuni.MesajErrApp;
import entities.Nota;
import entities.Student;
import entities.Tema;
import events.NotaChangeEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import observer.Observer;
import service.ServiceNota;
import service.ServiceStudent;
import service.ServiceTema;
import structuraAn.StructuraAn;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NotaController implements Observer<NotaChangeEvent> {
    private ServiceNota serviceNota=new ServiceNota();
    private ServiceTema serviceTema=new ServiceTema();
    private ServiceStudent serviceStudent=new ServiceStudent();
    private StructuraAn structuraAn=new StructuraAn();
    private ObservableList<Nota> modelNota= FXCollections.observableArrayList();
    private ObservableList<Student> modelStud= FXCollections.observableArrayList();
    private ObservableList<Integer> modelTeme= FXCollections.observableArrayList();

    private String prof;

    public void setProf(String prof) {
        this.prof = prof;
    }

    @FXML
    private ComboBox<Integer> temeBox;

    @FXML
    private TextField numeStud;

    @FXML
    private TableView<Student> tableStud;

    @FXML
    private TableColumn<Student,String> colIdSt;

    @FXML
    private TableColumn<Student,String> colNumeSt;

    @FXML
    private TableColumn<Student,String> colPreSt;

    @FXML
    private RadioButton radioMotivare;

    @FXML
    private RadioButton radioIntarz;

    @FXML
    private TextField notaVal;

    @FXML
    private TextArea feedback;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnUpd;

    @FXML
    private TableView<Nota> tableNota;

    @FXML
    private TableColumn<Nota,String> colNumeN;

    @FXML
    private TableColumn<Nota,String> colPreN;

    @FXML
    private TableColumn<Nota,String> colTemN;

    @FXML
    private TableColumn<Nota,String> colNotN;

    @FXML
    private TableColumn<Nota,String> colDataN;

    @FXML
    private TableColumn<Nota,String> colFbN;

    @FXML
    private TableColumn<Nota,String> colProfN;

    @FXML ComboBox<String> rapoarteBox;

    private void initModel() {
        List<Nota> notaList=(List<Nota>)serviceNota.findAllService();
        modelNota.setAll(notaList);
    }

    @Override
    public void update(NotaChangeEvent NotaChangeEvent) {
        initModel();
    }

    @FXML
    public void initialize(){
        serviceNota.addObserver(this);
        initModel();
        //modelNota.setAll(((List<Nota>)serviceNota.findAllService()));
        tableNota.setItems(modelNota);
        colNumeN.setCellValueFactory(new PropertyValueFactory<Nota,String>("nume"));
        colPreN.setCellValueFactory(new PropertyValueFactory<Nota,String>("prenume"));
        colTemN.setCellValueFactory(new PropertyValueFactory<Nota,String>("tema"));
        colNotN.setCellValueFactory(new PropertyValueFactory<Nota,String>("nota"));
        colDataN.setCellValueFactory(new PropertyValueFactory<Nota,String>("data"));
        colFbN.setCellValueFactory(new PropertyValueFactory<Nota,String>("feedback"));
        colProfN.setCellValueFactory(new PropertyValueFactory<Nota,String>("profesor"));

        int tema=-1;
        for (Tema t:serviceTema.getAll()
             ) {
            modelTeme.add(t.getId());
            System.out.println(t.getDeadlineWeek());
            if(t.getDeadlineWeek()>=structuraAn.getSaptamanaCurenta() && t.getStartWeek()<=structuraAn.getSaptamanaCurenta())
                tema=t.getId();
        }
        if(tema!=-1)
            temeBox.setValue(tema);
        temeBox.setItems(modelTeme);

        int finalTema = tema;
        temeBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(temeBox.getValue()!= finalTema){
                    radioMotivare.setVisible(true);
                    radioIntarz.setVisible(true);
                }
                else{
                    radioIntarz.setVisible(false);
                    radioMotivare.setVisible(false);
                }
                System.out.println(structuraAn.getSaptamanaCurenta());
                System.out.println(finalTema);
            }
        });
        numeStud.textProperty().addListener((observable,oldValue,newValue)->{
            tableStud.setVisible(true);
            modelStud.setAll((List<Student>)serviceStudent.findAllService());
            tableStud.setItems(modelStud);
            colIdSt.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));
            colNumeSt.setCellValueFactory(new PropertyValueFactory<Student,String>("nume"));
            colPreSt.setCellValueFactory(new PropertyValueFactory<Student,String>("prenume"));
            modelStud.setAll(((List<Student>)serviceStudent.findAllService()).stream()
                    .filter(x->x.getNume().startsWith(numeStud.getText())||x.getPrenume().startsWith(numeStud.getText()))
                    .collect(Collectors.toList())
            );
        });

        btnDel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Nota nota = tableNota.getSelectionModel().getSelectedItem();
                if (!nota.getProfesor().equals(prof)) {
                    MesajErrApp mesajErrApp = new MesajErrApp();
                    mesajErrApp.setMsg("Nu se poate sterge o nota acordata de alt profesor");
                    try {
                        mesajErrApp.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Nota t = tableNota.getSelectionModel().getSelectedItem();
                    serviceNota.deleteService(t.getId());
                    //modelNota.setAll((List<Nota>) serviceNota.findAllService());
                }
            }
        });

        btnUpd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Nota nota = tableNota.getSelectionModel().getSelectedItem();
                if (!nota.getProfesor().equals(prof)) {
                    MesajErrApp mesajErrApp = new MesajErrApp();
                    mesajErrApp.setMsg("Nu se poate modifica o nota acordata de alt profesor");
                    try {
                        mesajErrApp.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {

                    UpdateNotaApp updateNotaApp = new UpdateNotaApp();
                    //updateNotaApp.setModelNota(modelNota);
                    updateNotaApp.setServiceNota(serviceNota);
                    updateNotaApp.setId(nota.getId());
                    try {
                        updateNotaApp.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Map<Student, Tema> map = new HashMap<Student, Tema>();
                map.put(tableStud.getSelectionModel().getSelectedItem(),serviceTema.findOneService(temeBox.getValue()));

                String motivare="n";
                if(radioMotivare.isSelected()){
                    motivare="y";
                }
                if(radioIntarz.isSelected())
                    motivare="y";

                Tema t=serviceTema.findOneService(temeBox.getValue());
                System.out.println(motivare);
                System.out.println(t.getDeadlineWeek());
                int notaMax=serviceNota.getNotaMaxima(t.getDeadlineWeek(),motivare);
                System.out.println(notaMax);

                if(notaMax<10){
                    feedback.setText("Nota a fost diminuata cu " +(10-notaMax)+" puncte datorita intarzierilor");
                }
                else{
                    feedback.clear();
                }

                if(feedback.getText().equals("")){
                    feedback.setText("Nicio observatie");
                }

                if(Integer.parseInt(notaVal.getText())>notaMax){
                    MesajErrApp mesajErrApp=new MesajErrApp();
                    mesajErrApp.setMsg("Nota invalida");
                    try {
                        mesajErrApp.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(tableStud.getSelectionModel().isEmpty()){
                    MesajErrApp mesajErrApp=new MesajErrApp();
                    mesajErrApp.setMsg("Nu a fost selectat niciun student");
                    try {
                        mesajErrApp.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(temeBox.getSelectionModel().isEmpty()){
                    MesajErrApp mesajErrApp=new MesajErrApp();
                    mesajErrApp.setMsg("Nu a fost selectata nicio nota");
                    try {
                        mesajErrApp.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Nota nota=new Nota(map, LocalDate.now(),prof,Integer.parseInt(notaVal.getText()),feedback.getText());
                    VizualizareNotaApp vizualizareNotaApp=new VizualizareNotaApp();
                    vizualizareNotaApp.setNota(nota);
                    //vizualizareNotaApp.setModelNota(modelNota);
                    vizualizareNotaApp.setServiceNota(serviceNota);
                    try {
                        vizualizareNotaApp.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        ObservableList<String> rapModel=FXCollections.observableArrayList();
        rapModel.add("1. Media laborator pentru fiecare student");
        rapModel.add("2. Cea mai grea tema");
        rapModel.add("3. Studentii care pot intra in examen");
        rapModel.add("4. Studentii care au predat la timp toate temele");
        rapoarteBox.setItems(rapModel);
        rapoarteBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(rapoarteBox.getValue().equals("1. Media laborator pentru fiecare student")) {
                    RapoarteApp rapoarteApp=new RapoarteApp();
                    rapoarteApp.setMes(serviceNota.rap1());
                    try {
                        rapoarteApp.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                     }
                }
                else if(rapoarteBox.getValue().equals("2. Cea mai grea tema")){
                    RapoarteApp rapoarteApp=new RapoarteApp();
                    rapoarteApp.setMes(serviceNota.rap2());
                    try {
                        rapoarteApp.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(rapoarteBox.getValue().equals("3. Studentii care pot intra in examen")){
                    RapoarteApp rapoarteApp=new RapoarteApp();
                    rapoarteApp.setMes(serviceNota.rap3());
                    try {
                        rapoarteApp.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    RapoarteApp rapoarteApp=new RapoarteApp();
                    rapoarteApp.setMes(serviceNota.rap4());
                    try {
                        rapoarteApp.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
