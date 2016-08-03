package by.lostFinder.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SimpleService<E> {

    E save(E entity);
    E getById(String id);
    void delete(String id);
    Page<E> getAll(Pageable pageable);
}
