package by.lostFinder.services;

import java.util.List;

public interface SimpleService<E> {

    E save(E entity);
    E getById(long id);
    void delete(long id);
    List<E> getAll();
}
