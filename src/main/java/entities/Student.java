package entities;

import java.util.Objects;

public class Student extends Entity<Integer>{
    /**
     * Clasa obiectelor de tip Student
     */
    private String nume;
    private String prenume;
    private Integer grupa;
    private String email;
    private String cadruDidacticIndrumatorLab;

    @Override
    public String toString() {
        return "Student{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", grupa=" + grupa +
                ", email='" + email + '\'' +
                ", cadruDidacticIndrumatorLab='" + cadruDidacticIndrumatorLab + '\'' +
                '}';
    }

    public Student(Integer id, String nume, String prenume, Integer grupa, String email, String cadruDidacticIndrumatorLab) {
        /**
         * Constructor Student
         * @param id-ID, id instantei de tipul Student
         * @param nume-String, numele instantei de tipul Student
         * @param prenume-String, prenumele instantei de tipul Student
         * @param grupa-Integer, grupa instantei de tipul Student
         * @param email-String, emailul instantei de tipul Student
         * @param cadruDidacticIndrumatorLab-String, cadruDidacticIndrumatorLab instantei de tipul Student
         */
        this.setId(id);
        this.nume = nume;
        this.prenume = prenume;
        this.grupa = grupa;
        this.email = email;
        this.cadruDidacticIndrumatorLab = cadruDidacticIndrumatorLab;
    }

    public String getNume() {
        /**
         * Getter nume
         * @param
         * @return nume-String, numele instantei de tipul Student
         */
        return nume;
    }

    public void setNume(String nume) {
        /**
         * Setter nume
         * @param nume-String, numele instantei de tipul Student
         * @return
         */
        this.nume = nume;
    }

    public String getPrenume() {
        /** Getter prenume
         * @param
         * @return prenume-String, prenumele instantei de tipul Student
         */
        return prenume;
    }

    public void setPrenume(String prenume) {
        /**
         * Setter prenume
         * @param prenume-String, prenumele instantei de tipul Student
         * @return
         */
        this.prenume = prenume;
    }

    public Integer getGrupa() {
        /** Getter grupa
         * @param
         * @return grupa-Integer, grupa instantei de tipul Student
         */
        return grupa;
    }

    public void setGrupa(Integer grupa) {
        /**
         * Setter grupa
         * @param grupa-Integer, grupa instantei de tipul Student
         * @return
         */
        this.grupa = grupa;
    }

    public String getEmail() {
        /** Getter email
         * @param
         * @return email-String, emailul instantei de tipul Student
         */
        return email;
    }

    public void setEmail(String email) {
        /**
         * Setter email
         * @param email-String, emailul instantei de tipul Student
         * @return
         */
        this.email = email;
    }

    public String getCadruDidacticIndrumatorLab() {
        /** Getter cadruDidacticIndrumatorLab
         * @param
         * @return cadruDidacticIndrumatorLab-String, cadruDidacticIndrumatorLab instantei de tipul Student
         */
        return cadruDidacticIndrumatorLab;
    }

    public void setCadruDidacticIndrumatorLab(String cadruDidacticIndrumatorLab) {
        /**
         * Setter cadruDidacticIndrumatorLab
         * @param cadruDidacticIndrumatorLab-String, cadruDidacticIndrumatorLab instantei de tipul Student
         * @return
         */
        this.cadruDidacticIndrumatorLab = cadruDidacticIndrumatorLab;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(nume, student.nume) &&
                Objects.equals(prenume, student.prenume) &&
                Objects.equals(grupa, student.grupa) &&
                Objects.equals(email, student.email) &&
                Objects.equals(cadruDidacticIndrumatorLab, student.cadruDidacticIndrumatorLab);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, grupa, email, cadruDidacticIndrumatorLab);
    }
}

