package repository;

import entities.Student;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class StudentFileRepository extends RepositoryStudent{
    private String fileName;

    public StudentFileRepository(String fileName){
        this.fileName=fileName;
        loadData();
    }

    private void loadData() {
        try {
            Document document=DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(this.fileName);

            Element root=document.getDocumentElement();
            NodeList children=root.getChildNodes();
            for(int i=0;i<children.getLength();i++){
                Node elem=children.item(i);
                if(elem instanceof Element){
                    Student s=getStudentFromElement((Element)elem);
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

    @Override
    public Student save(Student entity){
        Student s=super.save(entity);
        writeToFile(super.findAll());
        return s;
    }

    @Override
    public Student delete(Integer id){
        Student s=super.delete(id);
        writeToFile(super.findAll());
        return s;
    }

    @Override
    public Student update(Student entity){
        Student s=super.update(entity);
        writeToFile(super.findAll());
        return s;
    }

    private void writeToFile(Iterable<Student> list) {
        try {
            Document document=DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            Element root=document.createElement("students");
            document.appendChild(root);
            super.findAll().forEach(s->{
                Node e=getElementFromStudent(document,s);
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

    private Node getElementFromStudent(Document document, Student entity) {
        Element student=document.createElement("student");
        student.setAttribute("id",entity.getId().toString());
        student.appendChild(getStudentElements(document,student,"nume",entity.getNume()));
        student.appendChild(getStudentElements(document,student,"prenume",entity.getPrenume()));
        student.appendChild(getStudentElements(document,student,"grupa",entity.getGrupa().toString()));
        student.appendChild(getStudentElements(document,student,"email",entity.getEmail()));
        student.appendChild(getStudentElements(document,student,"prof",entity.getCadruDidacticIndrumatorLab()));
        return student;
    }

    private Node getStudentElements(Document document, Element student, String nume, String nume1) {
        Element node=document.createElement(nume);
        node.appendChild(document.createTextNode(nume1));
        return node;
    }

    private Student getStudentFromElement(Element eElement){
        Integer id=Integer.parseInt(eElement.getAttribute("id"));
        String nume= eElement.getElementsByTagName("nume").item(0).getTextContent();
        String prenume= eElement.getElementsByTagName("prenume").item(0).getTextContent();
        Integer grupa=Integer.parseInt(eElement.getElementsByTagName("grupa").item(0).getTextContent());
        String email= eElement.getElementsByTagName("email").item(0).getTextContent();
        String prof= eElement.getElementsByTagName("prof").item(0).getTextContent();
        Student s=new Student(id,nume,prenume,grupa,email,prof);
        return s;
    }
}
