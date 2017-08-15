package com.bushemi.dao;

import java.util.Collection;

/**
 * Created by igor on 16.08.17.
 */
public interface CrudOperationsInterface<E> {
    void addEntityToDB(E entity);
    E loadEntityFromDb(long id);
    void deleteEntityFromDb(E entity);
    void updateEntityInDb(E entity);
    Collection<E> getAll();

}
