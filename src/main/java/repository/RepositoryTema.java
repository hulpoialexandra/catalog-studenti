package repository;

import entities.Tema;
import validator.ValidationException;
import validator.ValidatorTema;

import java.util.ArrayList;

public class RepositoryTema extends AbstractRepository<Integer,Tema> {
    private ValidatorTema validatorTema=new ValidatorTema();

    @Override
    public Tema save(Tema entity) throws ValidationException {
        if(entity==null)
            throw new IllegalArgumentException("Entitate vida\n");
        validatorTema.validate(entity);
        if(findOne(entity.getId())!=null)
            return findOne(entity.getId());
        ArrayList<Tema> l=super.getList();
        l.add(entity);
        super.setList(l);
        return null;
    }


    @Override
    public Tema update(Tema entity) {
        if(entity==null)
            throw new IllegalArgumentException("Entitate vida\n");
        validatorTema.validate(entity);
        if(findOne(entity.getId())==null)
            return entity;
        for(Tema t:super.getList()){
            if(t.getId().equals(entity.getId())){
                t.setDescriere(entity.getDescriere());
                t.setStartWeek(entity.getStartWeek());
                t.setDeadlineWeek(entity.getDeadlineWeek());
            }
        }
        return null;
    }
}
