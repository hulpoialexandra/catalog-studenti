package repository;

import entities.Entity;
import entities.Student;
import validator.ValidationException;
import validator.Validator;
import validator.ValidatorStudent;

import java.util.ArrayList;

public class RepositoryStudent extends AbstractRepository<Integer,Student> {
    private ValidatorStudent validatorStudent=new ValidatorStudent();


    @Override
    public Student save(Student entity) throws ValidationException {
        if(entity==null)
            throw new IllegalArgumentException("Entitate vida\n");
        validatorStudent.validate(entity);
        if(findOne(entity.getId())!=null)
            return findOne(entity.getId());
        ArrayList<Student> l=super.getList();
        l.add(entity);
        super.setList(l);
        return null;
    }

    @Override
    public Student update(Student entity) throws ValidationException{
        if(entity==null)
            throw new IllegalArgumentException("Entitate vida\n");
        validatorStudent.validate(entity);
        if(findOne(entity.getId())==null)
            return entity;
        for(Student s:super.getList()){
            if(s.getId()==entity.getId()){
                s.setNume(entity.getNume());
                s.setPrenume(entity.getPrenume());
                s.setGrupa(entity.getGrupa());
                s.setEmail(entity.getEmail());
                s.setCadruDidacticIndrumatorLab(entity.getCadruDidacticIndrumatorLab());
            }
        }
        return null;

    }
}
