package entities;

import java.util.Objects;

public class Tema extends Entity<Integer> {
    private String descriere;
    private Integer startWeek;
    private Integer deadlineWeek;

    public Tema(Integer id,String descriere, Integer startWeek, Integer deadlineWeek) {
        /**
         * Constructor Tema
         * @param id-ID, id instantei de tipul Tema
         * @param descriere-String, descrierea instantei de tipul Tema
         * @param startWeek-Integer, saptamana cand a fost adaugata Tema
         * @param deadlineWeek-Integer, saptamana de predare a temei
         */
        this.setId(id);
        this.descriere = descriere;
        this.startWeek = startWeek;
        this.deadlineWeek = deadlineWeek;
    }

    @Override
    public String toString() {
        return "Tema{" +
                "descriere='" + descriere + '\'' +
                ", startWeek=" + startWeek +
                ", deadlineWeek=" + deadlineWeek +
                '}';
    }

    public String getDescriere() {
        /**
         * Getter descriere
         * @param
         * @return descriere-String, descrierea instantei de tipul Tema
         */
        return descriere;
    }

    public void setDescriere(String descriere) {
        /**
         * Setter descriere
         * @param descriere-String, descrierea instantei de tipul Tema
         * @return
         */
        this.descriere = descriere;
    }

    public Integer getStartWeek() {
        /**
         * Getter startWeek
         * @param
         * @return startWeek-Integer, saptamana cand a fost adaugata Tema
         */
        return startWeek;
    }

    public void setStartWeek(Integer startWeek) {
        /**
         * Setter startWeek
         * @param startWeek-Integer, saptamana cand a fost adaugata tema
         * @return
         */
        this.startWeek = startWeek;
    }

    public Integer getDeadlineWeek() {
        /**
         * Getter deadlineWeek
         * @param
         * @return deadlineWeek-Integer, saptamana de predare a temei
         */
        return deadlineWeek;
    }

    public void setDeadlineWeek(Integer deadlineWeek) {
        /**
         * Setter deadlineWeek
         * @param deadlineWeek-Integer, saptamana de predare a temei
         * @return
         */
        this.deadlineWeek = deadlineWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tema tema = (Tema) o;
        return Objects.equals(descriere, tema.descriere) &&
                Objects.equals(startWeek, tema.startWeek) &&
                Objects.equals(deadlineWeek, tema.deadlineWeek);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriere, startWeek, deadlineWeek);
    }
}
