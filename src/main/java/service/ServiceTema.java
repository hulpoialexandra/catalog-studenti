package service;

import structuraAn.StructuraAn;
import entities.Tema;
import repository.RepositoryTema;
import repository.TemaFileRepository;

import java.util.List;

public class ServiceTema implements Service<Integer, Tema> {
    //private RepositoryTema rep=new RepositoryTema();
    private TemaFileRepository rep=new TemaFileRepository("src/main/resources/teme.xml");
    private StructuraAn structuraAn=new StructuraAn();

    public int getSaptCurenta(){
        return structuraAn.getSaptamanaCurenta();
    }

    @Override
    public Tema findOneService(Integer id) {
        return rep.findOne(id);
    }

    @Override
    public Iterable<Tema> findAllService() {
        return rep.findAll();
    }

    @Override
    public Tema saveService(Tema entity) {
        return rep.save(entity);
    }

    @Override
    public Tema deleteService(Integer id) {
        return rep.delete(id);
    }

    @Override
    public Tema updateService(Tema entity) {
        return rep.update(entity);
    }

    public List<Tema> getAll() {
        return rep.getList();
    }
}
