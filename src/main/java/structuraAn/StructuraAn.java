package structuraAn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StructuraAn {
    //int anUniversitar;
    int semestru;

    private LocalDate startSemester1;
    private LocalDate beginHoliday1;
    private LocalDate endHoliday1;
    private LocalDate endSemester1;
    private String fileName1;

    private LocalDate startSemester2;
    private LocalDate beginHoliday2;
    private LocalDate endHoliday2;
    private LocalDate endSemester2;
    private String fileName2;

    public StructuraAn() {
        fileName1="src/main/resources/sem1.txt";
        fileName2="src/main/resources/sem2.txt";
        loadData();
    }

    private void loadData() {
        Path p1= Paths.get(fileName1);
        try{
            List<String> lines1= Files.readAllLines(p1);
            startSemester1=LocalDate.parse(lines1.get(0), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            beginHoliday1=LocalDate.parse(lines1.get(1), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            endHoliday1=LocalDate.parse(lines1.get(2), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            endSemester1=LocalDate.parse(lines1.get(3), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Path p2= Paths.get(fileName2);
        try{
            List<String> lines2= Files.readAllLines(p2);
            startSemester2=LocalDate.parse(lines2.get(0), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            beginHoliday2=LocalDate.parse(lines2.get(1), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            endHoliday2=LocalDate.parse(lines2.get(2), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            endSemester2=LocalDate.parse(lines2.get(3), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getSaptamanaCurenta(){
        LocalDate data=LocalDate.now();
        if(data.isBefore(startSemester1)) return -1;
        if(data.isAfter(beginHoliday1)&&data.isBefore(endHoliday1)) return -1;
        if(data.isAfter(endSemester1)&&data.isBefore(startSemester2)) return -1;
        if(data.isAfter(beginHoliday2)&&data.isBefore(endHoliday2)) return -1;
        if(data.isAfter(endSemester2)) return -1;
        int sc=0;
        if(data.isBefore(beginHoliday1)){
            sc=(data.getDayOfYear()-startSemester1.getDayOfYear())/7;
        }
        else if(data.isAfter(endHoliday1)&&data.isBefore(endSemester1)){
            int sv=(beginHoliday1.getDayOfYear()-endHoliday1.getDayOfYear())/7;
            sc=(data.getDayOfYear()-startSemester1.getDayOfYear()-sv);
        }
        else if(data.isAfter(startSemester2)&&data.isBefore(beginHoliday2)){
            sc=(data.getDayOfYear()-startSemester2.getDayOfYear())/7;
        }
        else if(data.isAfter(endHoliday2)&& data.isBefore(endSemester2)){
            int sv=(beginHoliday2.getDayOfYear()-endHoliday2.getDayOfYear())/7;
            sc=(data.getDayOfYear()-startSemester2.getDayOfYear()-sv)/7;
        }
        return sc+1;
    }

    public int getSemestru() {
        LocalDate data=LocalDate.now();
        if(data.isAfter(startSemester1)&&data.isBefore(endSemester1))
            return 1;
        return 2;
    }

    public void setSemestru(int semestru) {
        this.semestru = semestru;
    }

    public LocalDate getStartSemester1() {
        return startSemester1;
    }

    public void setStartSemester1(LocalDate startSemester1) {
        this.startSemester1 = startSemester1;
    }

    public LocalDate getBeginHoliday1() {
        return beginHoliday1;
    }

    public void setBeginHoliday1(LocalDate beginHoliday1) {
        this.beginHoliday1 = beginHoliday1;
    }

    public LocalDate getEndHoliday1() {
        return endHoliday1;
    }

    public void setEndHoliday1(LocalDate endHoliday1) {
        this.endHoliday1 = endHoliday1;
    }

    public LocalDate getEndSemester1() {
        return endSemester1;
    }

    public void setEndSemester1(LocalDate endSemester1) {
        this.endSemester1 = endSemester1;
    }

    public LocalDate getStartSemester2() {
        return startSemester2;
    }

    public void setStartSemester2(LocalDate startSemester2) {
        this.startSemester2 = startSemester2;
    }

    public LocalDate getBeginHoliday2() {
        return beginHoliday2;
    }

    public void setBeginHoliday2(LocalDate beginHoliday2) {
        this.beginHoliday2 = beginHoliday2;
    }

    public LocalDate getEndHoliday2() {
        return endHoliday2;
    }

    public void setEndHoliday2(LocalDate endHoliday2) {
        this.endHoliday2 = endHoliday2;
    }

    public LocalDate getEndSemester2() {
        return endSemester2;
    }

    public void setEndSemester2(LocalDate endSemester2) {
        this.endSemester2 = endSemester2;
    }

    public int getSaptamanaData(LocalDate data){
        if(data.isBefore(startSemester1)) return -1;
        if(data.isAfter(beginHoliday1)&&data.isBefore(endHoliday1)) return -1;
        if(data.isAfter(endSemester1)&&data.isBefore(startSemester2)) return -1;
        if(data.isAfter(beginHoliday2)&&data.isBefore(endHoliday2)) return -1;
        if(data.isAfter(endSemester2)) return -1;
        int sc=0;
        if(data.isBefore(beginHoliday1)){
            sc=(data.getDayOfYear()-startSemester1.getDayOfYear())/7;
        }
        else if(data.isAfter(endHoliday1)&&data.isBefore(endSemester1)){
            int sv=(beginHoliday1.getDayOfYear()-endHoliday1.getDayOfYear())/7;
            sc=(data.getDayOfYear()-startSemester1.getDayOfYear()-sv);
        }
        else if(data.isAfter(startSemester2)&&data.isBefore(beginHoliday2)){
            sc=(data.getDayOfYear()-startSemester2.getDayOfYear())/7;
        }
        else if(data.isAfter(endHoliday2)&& data.isBefore(endSemester2)){
            int sv=(beginHoliday2.getDayOfYear()-endHoliday2.getDayOfYear())/7;
            sc=(data.getDayOfYear()-startSemester2.getDayOfYear()-sv)/7;
        }
        return sc+1;
    }
}
