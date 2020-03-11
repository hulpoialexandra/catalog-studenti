package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Dictionary;
import java.util.Map;

public class Nota extends Entity<Map> {
    private LocalDate data;
    private String profesor;
    private int nota;
    private String feedback;
    private String nume;
    private String prenume;
    private int tema;

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public Integer getTema() {
        return tema;
    }

    public Nota(Map id, LocalDate data, String profesor, int nota, String feedback) {
        super.setId(id);
        Tema t=(Tema)id.values().toArray()[0];
        tema=t.getId();
        Student s=(Student)id.keySet().toArray()[0];
        nume=s.getNume();
        prenume=s.getPrenume();
        this.data = data;
        this.profesor = profesor;
        this.nota=nota;
        this.feedback=feedback;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "data=" + data +
                ", profesor='" + profesor + '\'' +
                ", nota=" + nota +
                ", feedback='" + feedback + '\'' +
                '}';
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
}
