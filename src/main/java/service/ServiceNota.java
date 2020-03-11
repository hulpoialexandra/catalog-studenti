package service;

import events.ChangeEventType;
import events.NotaChangeEvent;
import observer.Observable;
import observer.Observer;
import repository.RepositoryStudent;
import structuraAn.StructuraAn;
import entities.Nota;
import entities.Student;
import entities.Tema;
import repository.NoteFileRepository;
import repository.RepositoryNota;
import repository.RepositoryTema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServiceNota implements Service<Map, Nota>, Observable<NotaChangeEvent> {
    //private RepositoryNota rep=new RepositoryNota();
    private StructuraAn structuraAn=new StructuraAn();
    private NoteFileRepository rep=new NoteFileRepository("src/main/resources/note.xml","src/main/resources/numeStudent.txt",structuraAn);

    //1. Media laborator pentru fiecare student
    public String rap1(){
        String rez="";
        ArrayList<Nota> note=(ArrayList<Nota>)rep.findAll();
        RepositoryStudent repStud=rep.getRepositoryStudent();
        RepositoryTema repTeme=rep.getRepositoryTema();
        ArrayList<Student> studenti=(ArrayList<Student>)repStud.findAll();
        ArrayList<Tema> teme=(ArrayList<Tema>)repTeme.findAll();
        Map<Student,Double> medii=new HashMap<>();
        for (Student s:studenti
             ) {
            medii.put(s,(double)0);
        }
        for (Nota n:note
             ) {
            Tema t=(Tema)n.getId().values().toArray()[0];
            Student s=(Student) n.getId().keySet().toArray()[0];
            medii.replace(s,medii.get(s)+(double)(n.getNota()*(t.getDeadlineWeek()-t.getStartWeek())));
        }
        double pond=0;
        for (Tema t:teme
             ) {
            pond+=t.getDeadlineWeek()-t.getStartWeek();
        }
        for (Student s:studenti
             ) {
            rez+=s.getNume()+" "+s.getPrenume()+" "+" are media "+medii.get(s)/pond;
            rez+="\n";
        }
        return rez;
    }

    public String rap2() {
        String rez="";
        ArrayList<Nota> note=(ArrayList<Nota>)rep.findAll();
        RepositoryStudent repStud=rep.getRepositoryStudent();
        RepositoryTema repTeme=rep.getRepositoryTema();
        ArrayList<Student> studenti=(ArrayList<Student>)repStud.findAll();
        ArrayList<Tema> teme=(ArrayList<Tema>)repTeme.findAll();
        double min=11;
        Tema tmin=null;
        Map<Tema,Double> medii=new HashMap<>();
        for (Tema t:teme
             ) {
            medii.put(t,(double)0);
        }
        for (Nota n:note
             ) {
            Tema t=(Tema)n.getId().values().toArray()[0];
            //Student s=(Student) n.getId().keySet().toArray()[0];
            medii.replace(t,medii.get(t)+(double)n.getNota());
        }
        double c=studenti.size();
        for (Tema t:teme
             ) {
            if(medii.get(t)/c<min){
                min=medii.get(t)/c;
                tmin=t;
            }
        }
        rez="Cea mai grea tema este tema: "+tmin.getId();
        return rez;
    }

    public String rap3(){
        String rez="";
        ArrayList<Nota> note=(ArrayList<Nota>)rep.findAll();
        RepositoryStudent repStud=rep.getRepositoryStudent();
        RepositoryTema repTeme=rep.getRepositoryTema();
        ArrayList<Student> studenti=(ArrayList<Student>)repStud.findAll();
        ArrayList<Tema> teme=(ArrayList<Tema>)repTeme.findAll();
        Map<Student,Double> medii=new HashMap<>();
        for (Student s:studenti
        ) {
            medii.put(s,(double)0);
        }
        for (Nota n:note
        ) {
            Tema t=(Tema)n.getId().values().toArray()[0];
            Student s=(Student) n.getId().keySet().toArray()[0];
            medii.replace(s,medii.get(s)+(double)(n.getNota()*(t.getDeadlineWeek()-t.getStartWeek())));
        }
        double pond=0;
        for (Tema t:teme
        ) {
            pond+=t.getDeadlineWeek()-t.getStartWeek();
        }
        for (Student s:studenti
             ) {
            if(medii.get(s)/pond>4){
                    rez += (s.getNume() + " " + s.getPrenume() + " poate intra in examen");
                    rez += "\n";
                }
                else{
                    rez += (s.getNume() + " " + s.getPrenume() + " nu poate intra in examen");
                    rez += "\n";
                }
        }
        return rez;
    }

    public String rap4(){
        String rez="";
        ArrayList<Nota> note=(ArrayList<Nota>)rep.findAll();
        RepositoryStudent repStud=rep.getRepositoryStudent();
        RepositoryTema repTeme=rep.getRepositoryTema();
        ArrayList<Student> studenti=(ArrayList<Student>)repStud.findAll();
        ArrayList<Tema> teme=(ArrayList<Tema>)repTeme.findAll();
        Map<Student,Integer> temePredate=new HashMap<>();
        for (Student s:studenti
        ) {
            temePredate.put(s,0);
        }
        for (Nota n:note
             ) {
            Student s=(Student)n.getId().keySet().toArray()[0];
            Tema t=(Tema)n.getId().values().toArray()[0];
            if(t.getDeadlineWeek()>=structuraAn.getSaptamanaData(n.getData())){
                temePredate.replace(s,temePredate.get(s)+1);
            }
        }
//        for (Nota n:note) {
//            Student s=(Student)n.getId().keySet().toArray()[0];
//            Tema t=(Tema)n.getId().values().toArray()[0];
//            if((!students.contains(s))&&t.getDeadlineWeek()>=structuraAn.getSaptamanaData(n.getData())){
//                students.add(s);
//            }
//            else if((students.contains(s))&&t.getDeadlineWeek()<structuraAn.getSaptamanaData(n.getData())){
//                students.remove(s);
//            }
//        }

        for (Student s:studenti
             ) {
            if(temePredate.get(s)==teme.size())
                rez+=(s.getNume()+" "+s.getPrenume()+" a predat toate temele la timp\n");
        }
        return rez;
    }

    public int getNotaMaxima(int sapt,String motivare){
        if(motivare.equals("n")) {
            int sc=structuraAn.getSaptamanaCurenta();
            if (sc <= sapt) return 10;
            if (sc - sapt == 1) return 9;
            if (sc - sapt == 2) return 8;
            return 0;
        }
        else{
            return 10;
        }
    }

//    public int getNotaMaximaMotivare(LocalDate data, Map<Student, Tema> map, String rasp,int val) {
//        if(rasp.equals("n")){
//            return val;
//        }
//        return 10;
//    }

    @Override
    public Nota findOneService(Map map) {
        return rep.findOne(map);
    }

    @Override
    public Iterable<Nota> findAllService() {
        return rep.findAll();
    }

    @Override
    public Nota saveService(Nota entity) {
        if(entity.getNota()<0)
            return entity;
        Nota n=rep.save(entity);
        if(n==null){
            notifyObservers(new NotaChangeEvent(ChangeEventType.ADD,n));
        }
        return n;
    }

    @Override
    public Nota deleteService(Map map) {

        Nota n= rep.delete(map);
        if(n!=null){
            notifyObservers(new NotaChangeEvent(ChangeEventType.DELETE,n));
        }
        return n;
    }

    @Override
    public Nota updateService(Nota entity) {
        Nota n= rep.update(entity);
        if(n==null){
            notifyObservers(new NotaChangeEvent(ChangeEventType.UPDATE,n));
        }
        return n;
    }

    //toti studentii care au predat o anumita tema
    public Iterable<Student> filtrare2(int temaId){
        List<Nota> rez=rep.getList().stream()
                .filter(x->{
                    Tema t=(Tema)x.getId().values().toArray()[0];
                    return t.getId()==temaId;
                })
                .collect(Collectors.toList());
        List<Student> rezFin=new ArrayList<>();
        for (Nota n:rez
        ) {
            Student s=(Student)n.getId().keySet().toArray()[0];
            rezFin.add(s);
        }
        return rezFin;
    }

    //toti studnetii care au predat o anumita tema unui profesor anume
    public Iterable<Student> filtrare3(int temaId, String prof){
        List<Nota> rez=rep.getList().stream()
                .filter(x->{
                    Tema t=(Tema)x.getId().values().toArray()[0];
                    return t.getId()==temaId && x.getProfesor().equals(prof);
                })
                .collect(Collectors.toList());
        List<Student> rezFin=new ArrayList<>();
        for (Nota n:rez
        ) {
            Student s=(Student)n.getId().keySet().toArray()[0];
            rezFin.add(s);
        }
        return rezFin;
    }

    //toate notele la o anumita tema, dintr-o saptamana data
    public Iterable<Nota> filtrare4(int temaId,int sapt){
        List<Nota> rez=rep.getList().stream()
                .filter(x->{
                    Tema t=(Tema)x.getId().values().toArray()[0];
                    return t.getId()==temaId && structuraAn.getSaptamanaData(x.getData())==sapt;
                })
                .collect(Collectors.toList());
        return rez;
    }

    private List<Observer<NotaChangeEvent>> observers=new ArrayList<>();
    @Override
    public void addObserver(Observer<NotaChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<NotaChangeEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(NotaChangeEvent t) {
        observers.stream().forEach(x->x.update(t));
    }
}
