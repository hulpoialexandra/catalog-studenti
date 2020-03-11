package repository;

import entities.Nota;
import entities.Student;
import entities.Tema;
import validator.ValidationException;
import validator.ValidatorNota;

import java.util.ArrayList;
import java.util.Map;

public class RepositoryNota extends AbstractRepository<Map, Nota> {

    private ValidatorNota validatorNota=new ValidatorNota();

    @Override
    public Nota findOne(Map id){
        ArrayList<Nota> l=super.getList();
        for (Nota e: l) {
            Tema t=(Tema)e.getId().values().toArray()[0];
            Student s=(Student)e.getId().keySet().toArray()[0];
            Tema tid=(Tema)id.values().toArray()[0];
            Student sid=(Student)id.keySet().toArray()[0];
            if(t.equals(tid)&&s.equals(sid))
                return e;
        }
        return null;
    }

    @Override
    public Nota save(Nota entity) throws ValidationException {
        if(entity==null)
            throw new IllegalArgumentException("Entitate vida\n");
        validatorNota.validate(entity);
        if(findOne(entity.getId())!=null)
            return findOne(entity.getId());
        ArrayList<Nota> l=super.getList();
        l.add(entity);
        super.setList(l);
        return null;
    }

    @Override
    public Nota update(Nota entity) throws ValidationException{
        if(entity==null)
            throw new IllegalArgumentException("Entitate vida\n");
        validatorNota.validate(entity);
        if(findOne(entity.getId())==null)
            return entity;
        for(Nota t:super.getList()){
            if(t.getId().equals(entity.getId())){
                t.setData(entity.getData());
                t.setProfesor(entity.getProfesor());
                t.setNota(entity.getNota());
                t.setFeedback(entity.getFeedback());
            }
        }
        return null;
    }
}
