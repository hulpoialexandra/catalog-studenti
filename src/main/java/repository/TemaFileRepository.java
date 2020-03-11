package repository;

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
import java.util.List;

public class TemaFileRepository extends RepositoryTema {
    private String fileName;

    public TemaFileRepository(String fileName) {
        this.fileName = fileName;
        loadData();
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
                    Tema s=getTemaFromElement((Element)elem);
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

    private Tema getTemaFromElement(Element eElement) {
        Integer id=Integer.parseInt(eElement.getAttribute("id"));
        String descriere= eElement.getElementsByTagName("descriere").item(0).getTextContent();
        Integer startWeek=Integer.parseInt(eElement.getElementsByTagName("startWeek").item(0).getTextContent());
        Integer deadlineWeek=Integer.parseInt(eElement.getElementsByTagName("deadlineWeek").item(0).getTextContent());
        Tema s=new Tema(id,descriere,startWeek,deadlineWeek);
        return s;
    }

    @Override
    public Tema save(Tema entity){
        Tema s=super.save(entity);
        writeToFile(super.findAll());
        return s;
    }

    @Override
    public Tema delete(Integer id){
        Tema s=super.delete(id);
        writeToFile(super.findAll());
        return s;
    }

    @Override
    public Tema update(Tema entity){
        Tema s=super.update(entity);
        writeToFile(super.findAll());
        return s;
    }

    private void writeToFile(Iterable<Tema> list) {
        try {
            Document document=DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            Element root=document.createElement("teme");
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

    private Node getElementFromTema(Document document, Tema entity) {
        Element t=document.createElement("tema");
        t.setAttribute("id",entity.getId().toString());
        t.appendChild(getTemaElements(document,t,"descriere",entity.getDescriere()));
        t.appendChild(getTemaElements(document,t,"startWeek",entity.getStartWeek().toString()));
        t.appendChild(getTemaElements(document,t,"deadlineWeek",entity.getDeadlineWeek().toString()));
        return t;
    }

    private Node getTemaElements(Document document, Element t, String descriere, String descriere1) {
        Element node=document.createElement(descriere);
        node.appendChild(document.createTextNode(descriere1));
        return node;
    }
}
