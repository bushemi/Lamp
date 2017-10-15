package com.bushemi.dao;

import java.util.Collection;

/**
 * Created by igor on 16.08.17.
 */
public interface CrudOperationsInterface<E> {
    Long create(E entity);
    E findById(long id);
    void delete(E entity);
    E update(E entity);
    Collection<E> findAll();

}
