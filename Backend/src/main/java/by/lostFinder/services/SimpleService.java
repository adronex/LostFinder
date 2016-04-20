package by.lostFinder.services;

import by.lostFinder.entities.superEntity.IdEntity;

import java.util.List;

/**
 * Created by М on 14.03.2016.
 */
public interface SimpleService<E extends IdEntity> {

    E save(E entity);
    E getById(long id);
    void delete(long id);
    List<E> getAll();
}