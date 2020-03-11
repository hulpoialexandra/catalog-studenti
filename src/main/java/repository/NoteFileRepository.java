package repository;

import structuraAn.StructuraAn;
import entities.Nota;
import entities.Student;
import entities.Tema;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteFileRepository extends RepositoryNota {
    private String fileName;
    private String file;
    private StudentFileRepository repositoryStudent=new StudentFileRepository("src/main/resources//students.xml");
    private TemaFileRepository repositoryTema=new TemaFileRepository("src/main/resources//teme.xml");
    private StructuraAn structuraAn;

    public StudentFileRepository getRepositoryStudent() {
        return repositoryStudent;
    }

    public TemaFileRepository getRepositoryTema() {
        return repositoryTema;
    }

    public NoteFileRepository(String fileName, String file, StructuraAn structuraAn) {
        this.fileName = fileName;
        this.file=file;
        this.structuraAn=structuraAn;
        loadData();
        writeToFileStud(super.findAll());
    }

    private void loadData() {
        try {
            Document document= DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(this.fileName);

            Element root=document.getDocumentElement();
            NodeList children=root.getChildNodes();
            for(int i=0;i<children.getLength();i++){
                Node elem=children.item(i);
                if(elem instanceof Element){
                    Nota s=getNotaFromElement((Element)elem);
                    super.save(s);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Nota getNotaFromElement(Element eElement) {
        Integer ids=Integer.parseInt(eElement.getAttribute("ids"));
        Integer idt=Integer.parseInt(eElement.getElementsByTagName("idt").item(0).getTextContent());
        Map<Student, Tema> map = new HashMap<Student, Tema>();
        map.put(repositoryStudent.findOne(ids),repositoryTema.findOne(idt));
        String dataS= eElement.getElementsByTagName("data").item(0).getTextContent();
        LocalDate data;
        data= LocalDate.parse(dataS,DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String prof= eElement.getElementsByTagName("prof").item(0).getTextContent();
        Integer val=Integer.parseInt(eElement.getElementsByTagName("val").item(0).getTextContent());
        String obs= eElement.getElementsByTagName("obs").item(0).getTextContent();
        Nota s=new Nota(map,data,prof,val,obs);
        return s;
    }

    @Override
    public Nota save(Nota entity){
        Nota s=super.save(entity);
        writeToFile(super.findAll());
        writeToFileStud(super.findAll());
        return s;
    }

    @Override
    public Nota delete(Map id){
        Nota s=super.delete(id);
        writeToFile(super.findAll());
        writeToFileStud(super.findAll());
        return s;
    }

    @Override
    public Nota update(Nota entity){
        Nota s=super.update(entity);
        writeToFile(super.findAll());
        writeToFileStud(super.findAll());
        return s;
    }

    private void writeToFileStud(Iterable<Nota> list) {
        Path p=Paths.get(file);
        try(BufferedWriter bw=Files.newBufferedWriter(p, StandardOpenOption.TRUNCATE_EXISTING)) {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(BufferedWriter bw=Files.newBufferedWriter(p, StandardOpenOption.WRITE)) {
            int i=0;
            for (Nota entity:list) {
                i++;
                if(i!=1)
                    bw.newLine();
                Tema t=(Tema)entity.getId().values().toArray()[0];
                Student s=(Student)entity.getId().keySet().toArray()[0];
                bw.write("Studentul: "+s.getNume()+" "+s.getPrenume());
                bw.newLine();
                bw.write("Tema: "+t.getId());
                bw.newLine();
                bw.write("Nota: "+entity.getNota());
                bw.newLine();
                bw.write("Predata in saptamana: "+structuraAn.getSaptamanaData(entity.getData()));
                bw.newLine();
                bw.write("Deadline: "+t.getDeadlineWeek());
                bw.newLine();
                bw.write("Feedback: "+ entity.getFeedback());
                bw.newLine();
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(Iterable<Nota> list) {
//        Path p=Paths.get(fileName);
//        try(BufferedWriter bw=Files.newBufferedWriter(p, StandardOpenOption.TRUNCATE_EXISTING)) {
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try(BufferedWriter bw=Files.newBufferedWriter(p, StandardOpenOption.WRITE)) {
//            int i=0;
//            for (var entity:list) {
//                i++;
//                if(i!=1)
//                    bw.newLine();
//                Tema t=(Tema)entity.getId().values().toArray()[0];
//                Student s=(Student)entity.getId().keySet().toArray()[0];
//                bw.write(s.getId()+";"+t.getId()+";"+entity.getData().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+";"+entity.getProfesor()+";"+entity.getNota()+";"+entity.getFeedback());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            Document document=DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            Element root=document.createElement("note");
            document.appendChild(root);
            super.findAll().forEach(s->{
                Node e=getElementFromTema(document,s);
                root.appendChild(e);
            });

            Transformer transformer= TransformerFactory.newInstance().newTransformer();
            Source source=new DOMSource((document));
            transformer.transform(source,new StreamResult((fileName)));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private Node getElementFromTema(Document document, Nota entity) {
        Element n=document.createElement("nota");
        Tema t=(Tema)entity.getId().values().toArray()[0];
        Student s=(Student)entity.getId().keySet().toArray()[0];
        //System.out.println(s);
        n.setAttribute("ids",s.getId().toString());
        n.appendChild(getNotaElements(document,n,"idt",t.getId().toString()));
        n.appendChild(getNotaElements(document,n,"data",entity.getData().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
        n.appendChild(getNotaElements(document,n,"prof",entity.getProfesor()));
        Integer no=entity.getNota();
        n.appendChild(getNotaElements(document,n,"val",no.toString()));
        n.appendChild(getNotaElements(document,n,"obs",entity.getFeedback()));
        return n;
    }

    private Node getNotaElements(Document document, Element n, String prof, String profesor) {
        Element node=document.createElement(prof);
        node.appendChild(document.createTextNode(profesor));
        return node;
    }

}
