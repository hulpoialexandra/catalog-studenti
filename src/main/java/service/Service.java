package service;

import entities.Entity;

public interface Service <ID,E extends Entity<ID>>{
    /**
     *
     * @param id-the id of the entity to be returned
     * @return the entity with the specified id
     * or null - if there is no entity with the given id
     */
    E findOneService(ID id);

    /**
     *
     * @return all entities
     */
    Iterable<E> findAllService();

    /**
     * save entity
     * @param entity
     * @return null- if the given entity is saved
     * otherwise returns the entity (id already exists)
     */
    E saveService(E entity);

    /**
     * removes the entity with the specified id
     * @param id
     * @return the removed entity or null if there is no entity with the
     */
    E deleteService(ID id);

    /**
     * update entity
     * @param entity
     * @return null - if the entity is updated,
     * otherwise returns the entity
     */
    E updateService(E entity);
}
