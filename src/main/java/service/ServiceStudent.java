package service;

import entities.Student;
import repository.RepositoryStudent;
import repository.StudentFileRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceStudent implements Service<Integer, Student> {
    //private RepositoryStudent rep=new RepositoryStudent();
    private StudentFileRepository rep=new StudentFileRepository("src/main/resources/students.xml");
    @Override
    public Student findOneService(Integer id) {
        return rep.findOne(id);
    }

    @Override
    public Iterable<Student> findAllService() {
        return rep.findAll();
    }

    @Override
    public Student saveService(Student entity) {
        return rep.save(entity);
    }

    @Override
    public Student deleteService(Integer id) {
        return rep.delete(id);
    }

    @Override
    public Student updateService(Student entity) {
        return rep.update(entity);
    }

    //toti studentii unei grupe
    public Iterable<Student> filtrare1(int grupa){
        List<Student> rez=rep.getList().stream()
                .filter(x->{
                    return x.getGrupa().equals(grupa);
                })
                .collect(Collectors.toList());
        return rez;
    }
}
