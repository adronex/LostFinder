package by.lostFinder.services.impl;

import by.lostFinder.repositories.superRepositories.SimpleRepository;
import by.lostFinder.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by М on 14.03.2016.
 */
@Service
public abstract class SimpleServiceImpl<E, R extends SimpleRepository<E>> implements SimpleService<E>{

    @Autowired
    protected R repository;

    @Override
    public E save(E entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public E getById(long id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(long id) {
        repository.delete(id);
    }

    @Override
    public List<E> getAll() {
        return repository.findAll();
    }

}
