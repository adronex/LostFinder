package by.lostFinder.services;

import java.util.List;

/**
 * Created by М on 14.03.2016.
 */
public interface SimpleService<E> {

    E save(E entity);
    E getById(long id);
    void delete(long id);
    List<E> getAll();
}
