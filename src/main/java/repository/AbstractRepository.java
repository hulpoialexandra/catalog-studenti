package repository;

import entities.Entity;
import entities.Student;
import validator.ValidationException;

import java.util.ArrayList;

public abstract class AbstractRepository<ID,E extends Entity<ID>> implements CrudRepository<ID,E> {
    private ArrayList<E> list=new ArrayList<>();

    public ArrayList<E> getList() {
        return list;
    }

    public void setList(ArrayList<E> list) {
        this.list = list;
    }

    @Override
    public E findOne(ID id) {
        if(id==null)
            throw new IllegalArgumentException("Id vid\n");
        for (E e:list) {
            if(e.getId()==id)
                return e;
        }
        return null;
    }

    @Override
    public Iterable<E> findAll() {
        if(list.size()>0)
            return list;
        return null;
    }

    @Override
    public abstract E save(E entity) throws ValidationException;

    @Override
    public E delete(ID id) {
        if(id==null)
            throw new IllegalArgumentException("Id vid\n");
        if(findOne(id)==null)
            return null;
        E st=findOne(id);
        int index=0;
        for (int i=0;i<list.size();i++) {
            if(list.get(i).getId()==id)
                index=i;
        }
        list.remove(index);
        return st;
    }

    @Override
    public abstract E update(E entity);
}
