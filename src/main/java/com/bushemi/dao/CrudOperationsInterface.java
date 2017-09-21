package com.bushemi.dao;

import java.util.Collection;

/**
 * Created by igor on 16.08.17.
 */
public interface CrudOperationsInterface<E> {
    void create(E entity);
    E findById(long id);
    void delete(E entity);
    void update(E entity);
    Collection<E> findAll();

}
