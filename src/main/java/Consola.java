//import entities.Nota;
//import entities.Student;
//import entities.Tema;
//import service.ServiceNota;
//import service.ServiceStudent;
//import service.ServiceTema;
//import validator.ValidationException;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//public class Consola {
//    private ServiceStudent serviceStudent=new ServiceStudent();
//    private ServiceTema serviceTema=new ServiceTema();
//    private ServiceNota serviceNota=new ServiceNota();
//
//    private void findOneStudentConsola()throws IOException{
//        System.out.println("Introduceti id: ");
//        InputStreamReader isr=new InputStreamReader(System.in);
//        BufferedReader bf=new BufferedReader(isr);
//        String x=bf.readLine();
//        int id;
//        if(!x.equals("")) {
//            try {
//                id = Integer.parseInt(x);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        try{
//            Student s=serviceStudent.findOneService(id);
//            if(s!=null)
//                System.out.println(s);
//            else
//                System.out.println("Nu exista niciun student cu acest id");
//        }
//        catch (IllegalArgumentException ex){
//            ex.printStackTrace();
//        }
//    }
//
//    private void getAllStudent(){
//        Iterable<Student> l=serviceStudent.findAllService();
//        if(l==null){
//            System.out.println("Nu exista studenti inregistrati");
//        }
//        for(Student a:l){
//            System.out.println(a);
//        }
//    }
//
//    private void addStudent() throws IOException {
//        System.out.println("Introduceti id:");
//        InputStreamReader isr=new InputStreamReader(System.in);
//        BufferedReader bf=new BufferedReader(isr);
//        String x=bf.readLine();
//        int id;
//        if(!x.equals("")) {
//            try {
//                id = Integer.parseInt(x);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        System.out.println("Introduceti nume:");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String nume=bf.readLine();
//        if(nume.equals("")){
//            System.out.println("Nume vid");
//            return;
//        }
//        System.out.println("Introduceti prenume:");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String prenume=bf.readLine();
//        if(prenume.equals("")){
//            System.out.println("Prenume vid");
//            return;
//        }
//        System.out.println("Introduceti grupa:");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String y=bf.readLine();
//        int grupa;
//        if(!y.equals("")) {
//            try {
//                grupa = Integer.parseInt(y);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Grupa trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Grupa vida");
//            return;
//        }
//        System.out.println("Introduceti email:");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String email=bf.readLine();
//        if(email.equals("")){
//            System.out.println("Email vid");
//            return;
//        }
//        System.out.println("Introduceti cadruDidacticIndrumatorLab:");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String cadruDidacticIndrumatorLab=bf.readLine();
//        if(cadruDidacticIndrumatorLab.equals("")){
//            System.out.println("Cadru didactic indrumator lab vid");
//            return;
//        }
//        Student s=new Student(id,nume,prenume,grupa,email,cadruDidacticIndrumatorLab);
//        try{
//            Student aux=serviceStudent.saveService(s);
//            if(aux!=null){
//                System.out.println("Id ul studentului exista deja");
//            }
//            else{
//                System.out.println("Student adaugat cu succes");
//            }
//        }
//        catch (ValidationException e){
//            System.out.println("Entitate invalidaa");
//            System.out.println(e.getMessage());
//        }
//        catch(IllegalArgumentException ex){
//            ex.printStackTrace();
//        }
//    }
//
//    private void deleteStudent() throws IOException {
//        System.out.println("Introduceti id: ");
//        InputStreamReader isr=new InputStreamReader(System.in);
//        BufferedReader bf=new BufferedReader(isr);
//        String x=bf.readLine();
//        int id;
//        if(!x.equals("")) {
//            try {
//                id = Integer.parseInt(x);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        try{
//            if(serviceStudent.deleteService(id)==null){
//                System.out.println("Nu exista student cu acest id");
//            }
//            else{
//                System.out.println("S-a sters studentul cu id-ul dat");
//            }
//        }
//        catch(IllegalArgumentException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    private void updateStudent() throws IOException {
//        System.out.println("Introduceti id: ");
//        InputStreamReader isr=new InputStreamReader(System.in);
//        BufferedReader bf=new BufferedReader(isr);
//        String x=bf.readLine();
//        int id;
//        if(!x.equals("")) {
//            try {
//                id = Integer.parseInt(x);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        System.out.println("Introduceti nume nou:");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String nume=bf.readLine();
//        if(nume.equals("")){
//            System.out.println("Nume vid");
//            return;
//        }
//        System.out.println("Introduceti prenume nou:");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String prenume=bf.readLine();
//        if(prenume.equals("")){
//            System.out.println("Prenume vid");
//            return;
//        }
//        System.out.println("Introduceti grupa noua:");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String y=bf.readLine();
//        int grupa;
//        if(!y.equals("")) {
//            try {
//                grupa = Integer.parseInt(y);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Grupa trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Grupa vida");
//            return;
//        }
//        System.out.println("Introduceti email nou:");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String email=bf.readLine();
//        if(email.equals("")){
//            System.out.println("Email vid");
//            return;
//        }
//        System.out.println("Introduceti cadruDidacticIndrumatorLab nou:");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String cadruDidacticIndrumatorLab=bf.readLine();
//        if(cadruDidacticIndrumatorLab.equals("")){
//            System.out.println("Cadru didactic indrumator lab vid");
//            return;
//        }
//        Student s=new Student(id,nume,prenume,grupa,email,cadruDidacticIndrumatorLab);
//        try{
//            if(serviceStudent.updateService(s)==null){
//                System.out.println("Modificarea s-a realizat cu succes");
//            }
//            else{
//                System.out.println("Nu s-a putut realiza modificarea");
//            }
//        }
//        catch(IllegalArgumentException ex){
//            ex.printStackTrace();
//        }
//        catch (ValidationException e){
//            System.out.println("Entitate invalida");
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private void findOneTemaConsola()throws IOException{
//        System.out.println("Introduceti id: ");
//        InputStreamReader isr=new InputStreamReader(System.in);
//        BufferedReader bf=new BufferedReader(isr);
//        String x=bf.readLine();
//        int id;
//        if(!x.equals("")) {
//            try {
//                id = Integer.parseInt(x);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        try{
//            Tema s=serviceTema.findOneService(id);
//            if(s!=null)
//                System.out.println(s);
//            else
//                System.out.println("Nu exista nicio tema cu acest id");
//        }
//        catch (IllegalArgumentException ex){
//            ex.printStackTrace();
//        }
//    }
//
//    private void getAllTema(){
//        Iterable<Tema> l=serviceTema.findAllService();
//        if(l==null){
//            System.out.println("Nu exista teme inregistrate.");
//            return;
//        }
//        for(Tema a:l){
//            System.out.println(a);
//        }
//    }
//
//    private void addTema() throws IOException {
//        System.out.println("Introduceti id: ");
//        InputStreamReader isr=new InputStreamReader(System.in);
//        BufferedReader bf=new BufferedReader(isr);
//        String x=bf.readLine();
//        int id;
//        if(!x.equals("")) {
//            try {
//                id = Integer.parseInt(x);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        System.out.println("Introduceti descriere: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String descriere=bf.readLine();
//        if(descriere.equals("")){
//            System.out.println("Descriere vida");
//            return;
//        }
//        System.out.println("Introduceti deadlineWeek: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String z=bf.readLine();
//        int deadlineWeek;
//        if(!z.equals("")){
//            try {
//                deadlineWeek = Integer.parseInt(z);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Deadline week trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("StartWeek vid");
//            return;
//        }
//        int startWeek=serviceTema.getSaptCurenta();
//        Tema s=new Tema(id,descriere,startWeek,deadlineWeek);
//        try{
//            Tema aux=serviceTema.saveService(s);
//            if(aux!=null){
//                System.out.println("Id ul temei exista deja");
//            }
//            else{
//                System.out.println("Tema adaugata cu succes");
//            }
//        }
//        catch(IllegalArgumentException ex){
//            ex.printStackTrace();
//        }
//        catch (ValidationException e){
//            System.out.println("Entitae invalida");
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private void updateTema() throws IOException {
//        System.out.println("Introduceti id: ");
//        InputStreamReader isr=new InputStreamReader(System.in);
//        BufferedReader bf=new BufferedReader(isr);
//        String x=bf.readLine();
//        int id;
//        if(!x.equals("")) {
//            try {
//                try {
//                    id = Integer.parseInt(x);
//                }
//                catch(NumberFormatException e){
//                    System.out.println("Id trebuie sa fie int");
//                    return;
//                }
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        System.out.println("Introduceti descriere noua: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String descriere=bf.readLine();
//        if(descriere.equals("")){
//            System.out.println("Descriere vida");
//            return;
//        }
//        System.out.println("Introduceti startWeek nou: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String y=bf.readLine();
//        int startWeek;
//        if(!y.equals("")){
//            try {
//                startWeek = Integer.parseInt(y);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Start week trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("StartWeek vid");
//            return;
//        }
//        System.out.println("Introduceti deadlineWeek nou: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String z=bf.readLine();
//        int deadlineWeek;
//        if(!z.equals("")){
//            try {
//                deadlineWeek = Integer.parseInt(z);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Deadline week trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("StartWeek vid");
//            return;
//        }
//        Tema s=new Tema(id,descriere,startWeek,deadlineWeek);
//        try{
//            if(serviceTema.updateService(s)==null){
//                System.out.println("Modificarea s-a realizat cu succes");
//            }
//            else{
//                System.out.println("Nu s-a putut realiza modificarea");
//            }
//        }
//        catch(IllegalArgumentException ex){
//            ex.printStackTrace();
//        }
//        catch (ValidationException e){
//            System.out.println("Entitate invalida");
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private void deleteTema() throws IOException {
//        System.out.println("Introduceti id: ");
//        InputStreamReader isr=new InputStreamReader(System.in);
//        BufferedReader bf=new BufferedReader(isr);
//        String x=bf.readLine();
//        int id;
//        if(!x.equals("")) {
//            try {
//                try {
//                    id = Integer.parseInt(x);
//                }
//                catch(NumberFormatException e){
//                    System.out.println("Id trebuie sa fie int");
//                    return;
//                }
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//
//        try{
//            if(serviceTema.deleteService(id)==null){
//                System.out.println("Nu exista tema cu acest id");
//            }
//            else{
//                System.out.println("S-a sters tema cu id-ul dat");
//            }
//        }
//        catch(IllegalArgumentException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    private void findOneNotaConsola()throws IOException{
//        System.out.println("Introduceti id student: ");
//        InputStreamReader isr=new InputStreamReader(System.in);
//        BufferedReader bf=new BufferedReader(isr);
//        String x=bf.readLine();
//        int id;
//        if(!x.equals("")) {
//            try {
//                id = Integer.parseInt(x);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        System.out.println("Introduceti id tema: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String y=bf.readLine();
//        int idt;
//        if(!y.equals("")) {
//            try {
//                idt = Integer.parseInt(y);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        try{
//            Map<Student, Tema> map = new HashMap<Student, Tema>();
//            map.put(serviceStudent.findOneService(id),serviceTema.findOneService(idt));
//            Nota s=serviceNota.findOneService(map);
//            if(s!=null)
//                System.out.println(s);
//            else
//                System.out.println("Nu exista nicio nota cu acest id");
//        }
//        catch (IllegalArgumentException ex){
//            ex.printStackTrace();
//        }
//    }
//
//    private void getAllNota(){
//        Iterable<Nota> l=serviceNota.findAllService();
//        if(l==null){
//            System.out.println("Nu exista note inregistrate.");
//            return;
//        }
//        for(Nota a:l){
//            System.out.println(a);
//        }
//    }
//
//    private void addNota() throws IOException {
//        System.out.println("Introduceti id student: ");
//        InputStreamReader isr=new InputStreamReader(System.in);
//        BufferedReader bf=new BufferedReader(isr);
//        String x=bf.readLine();
//        int id;
//        if(!x.equals("")) {
//            try {
//                id = Integer.parseInt(x);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        System.out.println("Introduceti id tema: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String y=bf.readLine();
//        int idt;
//        if(!y.equals("")) {
//            try {
//                idt = Integer.parseInt(y);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        LocalDate data;
//        System.out.println("Introduceti data: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String dataS=bf.readLine();
//        if(dataS.equals("")){
//            System.out.println("Data vida");
//            return;
//        }
//        else{
//            data= LocalDate.parse(dataS,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//        }
//        System.out.println("Introduceti profesor: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String profesor=bf.readLine();
//        if(profesor.equals("")){
//            System.out.println("Profesor vida");
//            return;
//        }
//        Map<Student, Tema> map = new HashMap<Student, Tema>();
//        map.put(serviceStudent.findOneService(id),serviceTema.findOneService(idt));
//        int val=serviceNota.getNotaMaxima(data,map,"n");
//        if(val==0){
//            System.out.println("Termenul maxim de predare a temei a expirat, tema nu mai poate fi predata.");
//            System.out.println("Studentul are motivare? (y/n)");
//            isr=new InputStreamReader(System.in);
//            bf=new BufferedReader(isr);
//            String rasp=bf.readLine();
//            val=serviceNota.getNotaMaxima(data,map,rasp);
//            if(val==0){
//                return;
//            }
//        }
//        else if(val<10){
//            System.out.println("Nota maxima pe care o poate primi stundentul este: "+val);
//            System.out.println("Studentul are motivare? (y/n)");
//            isr=new InputStreamReader(System.in);
//            bf=new BufferedReader(isr);
//            String rasp=bf.readLine();
//            val=serviceNota.getNotaMaxima(data,map,rasp);
//            System.out.println("Nota maxima pe care o poate lua studentul este: "+val);
//        }
//        System.out.println("Introduceti nota student: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String n=bf.readLine();
//        int nota;
//        if(!n.equals("")) {
//            try {
//                nota = Integer.parseInt(n);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Nota trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Nota vid");
//            return;
//        }
//
//        System.out.println("Introduceti feedback: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String fb=bf.readLine();
//        if(fb.equals("")){
//            fb="Nici o observatie";
//        }
//
//        Nota s=new Nota(map, data,profesor,nota,fb);
//        try{
//            Nota aux=serviceNota.saveService(s);
//            if(aux!=null){
//                System.out.println("Nota nu a putut fi adaugata");
//            }
//            else{
//                System.out.println("Nota adaugata cu succes");
//            }
//        }
//        catch(IllegalArgumentException ex){
//            ex.printStackTrace();
//        }
//        catch (ValidationException e){
//            System.out.println("Entitae invalida");
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private void updateNota() throws IOException {
//        System.out.println("Introduceti id student: ");
//        InputStreamReader isr=new InputStreamReader(System.in);
//        BufferedReader bf=new BufferedReader(isr);
//        String x=bf.readLine();
//        int id;
//        if(!x.equals("")) {
//            try {
//                id = Integer.parseInt(x);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        System.out.println("Introduceti id tema: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String y=bf.readLine();
//        int idt;
//        if(!y.equals("")) {
//            try {
//                idt = Integer.parseInt(y);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        Map<Student, Tema> map = new HashMap<Student, Tema>();
//        map.put(serviceStudent.findOneService(id),serviceTema.findOneService(idt));
//        LocalDate data;
//        System.out.println("Introduceti data: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String dataS=bf.readLine();
//        if(dataS.equals("")){
//            System.out.println("Data vida");
//            return;
//        }
//        else{
//            data= LocalDate.parse(dataS,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//        }
//        System.out.println("Introduceti profesor: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String profesor=bf.readLine();
//        if(profesor.equals("")){
//            System.out.println("Profesor vida");
//            return;
//        }
//        if(serviceNota.getNotaMaxima(data,map,"n")==0){
//            System.out.println("Termenul maxim de predare a temei a expirat, tema nu mai poate fi predata.");
//            return;
//        }
//        else{
//            System.out.println("Nota maxima pe care o poate primi stundentul este: "+serviceNota.getNotaMaxima(data,map,"n"));
//        }
//
//        System.out.println("Introduceti nota student: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String n=bf.readLine();
//        int nota;
//        if(!n.equals("")) {
//            try {
//                nota = Integer.parseInt(n);
//                if(nota<1){
//                    System.out.println("Nota trebuie sa fie intre 1 si 10");
//                    return;
//                }
//            }
//            catch(NumberFormatException e){
//                System.out.println("Nota trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Nota vid");
//            return;
//        }
//        if(nota>serviceNota.getNotaMaxima(data,map,"n")){
//            System.out.println("Nota este invalida");
//            return;
//        }
//
//        System.out.println("Introduceti feedback: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String fb=bf.readLine();
//        if(fb.equals("")){
//            fb="Nici o observatie";
//        }
//
//        Nota s=new Nota(map,data,profesor,nota,fb);
//        try{
//            if(serviceNota.updateService(s)==null){
//                System.out.println("Modificarea s-a realizat cu succes");
//            }
//            else{
//                System.out.println("Nu s-a putut realiza modificarea");
//            }
//        }
//        catch(IllegalArgumentException ex){
//            ex.printStackTrace();
//        }
//        catch (ValidationException e){
//            System.out.println("Entitate invalida");
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private void deleteNota() throws IOException {
//        System.out.println("Introduceti id student: ");
//        InputStreamReader isr=new InputStreamReader(System.in);
//        BufferedReader bf=new BufferedReader(isr);
//        String x=bf.readLine();
//        int id;
//        if(!x.equals("")) {
//            try {
//                id = Integer.parseInt(x);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        System.out.println("Introduceti id tema: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String y=bf.readLine();
//        int idt;
//        if(!y.equals("")) {
//            try {
//                idt = Integer.parseInt(y);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        Map<Student, Tema> map = new HashMap<Student, Tema>();
//        map.put(serviceStudent.findOneService(id),serviceTema.findOneService(idt));
//        try{
//            if(serviceNota.deleteService(map)==null){
//                System.out.println("Nu exista nota cu acest id");
//            }
//            else{
//                System.out.println("S-a sters nota cu id-ul dat");
//            }
//        }
//        catch(IllegalArgumentException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    private void filtrare1() throws IOException {
//        System.out.println("Dati grupa:");
//        InputStreamReader isr=new InputStreamReader(System.in);
//        BufferedReader bf=new BufferedReader(isr);
//        String y=bf.readLine();
//        int grupa;
//        if(!y.equals("")) {
//            try {
//                grupa = Integer.parseInt(y);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Grupa trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        Iterable<Student> list=serviceStudent.filtrare1(grupa);
//        for (Student s:list
//        ) {
//            System.out.println(s);
//        }
//    }
//
//
//    private void filtrare2() throws IOException {
//        System.out.println("Introduceti id tema: ");
//        InputStreamReader isr=new InputStreamReader(System.in);
//        BufferedReader bf=new BufferedReader(isr);
//        String y=bf.readLine();
//        int idt;
//        if(!y.equals("")) {
//            try {
//                idt = Integer.parseInt(y);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        Iterable<Student> list=serviceNota.filtrare2(idt);
//        for (Student s:list
//        ) {
//            System.out.println(s);
//        }
//    }
//
//    private void filtrare3() throws IOException {
//        System.out.println("Introduceti id tema: ");
//        InputStreamReader isr=new InputStreamReader(System.in);
//        BufferedReader bf=new BufferedReader(isr);
//        String y=bf.readLine();
//        int idt;
//        if(!y.equals("")) {
//            try {
//                idt = Integer.parseInt(y);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vid");
//            return;
//        }
//        System.out.println("Introduceti numele prfesorului: ");
//        isr=new InputStreamReader((System.in));
//        bf=new BufferedReader(isr);
//        String prof=bf.readLine();
//        if(prof.equals("")){
//            System.out.println("Profesor vid");
//            return;
//        }
//        Iterable<Student> list=serviceNota.filtrare3(idt,prof);
//        for (Student s:list
//        ) {
//            System.out.println(s);
//        }
//    }
//
//    private void filtrare4() throws IOException {
//        System.out.println("Introduceti id tema: ");
//        InputStreamReader isr=new InputStreamReader(System.in);
//        BufferedReader bf=new BufferedReader(isr);
//        String y=bf.readLine();
//        int idt;
//        if(!y.equals("")) {
//            try {
//                idt = Integer.parseInt(y);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Id trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Id vida");
//            return;
//        }
//        System.out.println("Introduceti saptamana: ");
//        isr=new InputStreamReader(System.in);
//        bf=new BufferedReader(isr);
//        String z=bf.readLine();
//        int sapt;
//        if(!y.equals("")) {
//            try {
//                sapt = Integer.parseInt(z);
//            }
//            catch(NumberFormatException e){
//                System.out.println("Saptamana trebuie sa fie int");
//                return;
//            }
//        }
//        else{
//            System.out.println("Saptamana vida");
//            return;
//        }
//        Iterable<Nota> list=serviceNota.filtrare4(idt,sapt);
//        for (Nota s:list
//        ) {
//            System.out.println(s);
//        }
//    }
//
//    public void run() throws IOException {
//        while (true) {
//            System.out.println("Meniul:");
//            System.out.println("0-exit");
//            System.out.println("STUDENTI:");
//            System.out.println("1-cautare");
//            System.out.println("2-getAll");
//            System.out.println("3-adaugare");
//            System.out.println("4-stergere");
//            System.out.println("5-update");
//            System.out.println("TEME:");
//            System.out.println("6-cautare");
//            System.out.println("7-getAll");
//            System.out.println("8-adaugare");
//            System.out.println("9-stergere");
//            System.out.println("10-update");
//            System.out.println("NOTE:");
//            System.out.println("11-cautare");
//            System.out.println("12-getAll");
//            System.out.println("13-adaugare");
//            System.out.println("14-stergere");
//            System.out.println("15-update");
//            System.out.println("FILTRARI:");
//            System.out.println("16-Toti studentii unei grupe");
//            System.out.println("17-Toti studentii care au predat o anumita tema");
//            System.out.println("18-Toti studentii care au predat o anumita tema unui anume profesor");
//            System.out.println("19-Toate notele la o anumita tema, dintr-o saptamana");
//            System.out.println("Alegeti instructiunea: ");
//            InputStreamReader isr = new InputStreamReader(System.in);
//            BufferedReader bf = new BufferedReader(isr);
//            String x = bf.readLine();
//            int com = Integer.parseInt(x);
//            switch (com) {
//                case 0:
//                    return;
//                case 1:
//                    findOneStudentConsola();
//                    break;
//                case 2:
//                    getAllStudent();
//                    break;
//                case 3:
//                    addStudent();
//                    break;
//                case 4:
//                    deleteStudent();
//                    break;
//                case 5:
//                    updateStudent();
//                    break;
//                case 6:
//                    findOneTemaConsola();
//                    break;
//                case 7:
//                    getAllTema();
//                    break;
//                case 8:
//                    addTema();
//                    break;
//                case 9:
//                    deleteTema();
//                    break;
//                case 10:
//                    updateTema();
//                    break;
//                case 11:
//                    findOneNotaConsola();
//                    break;
//                case 12:
//                    getAllNota();
//                    break;
//                case 13:
//                    addNota();
//                    break;
//                case 14:
//                    deleteNota();
//                    break;
//                case 15:
//                    updateNota();
//                    break;
//                case 16:
//                    filtrare1();
//                    break;
//                case 17:
//                    filtrare2();
//                    break;
//                case 18:
//                    filtrare3();
//                    break;
//                case 19:
//                    filtrare4();
//                    break;
//            }
//        }
//    }
//}
