package by.lostFinder.services;

import java.util.List;

public interface SimpleService<E> {

    E save(E entity);
    E getById(String id);
    void delete(String id);
    List<E> getAll();
}
