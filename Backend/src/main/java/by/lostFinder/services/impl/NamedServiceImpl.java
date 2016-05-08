package by.lostFinder.services.impl;

import by.lostFinder.entities.superEntity.NamedEntity;
import by.lostFinder.repositories.superRepositories.NamedRepository;
import by.lostFinder.services.NamedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by М on 14.03.2016.
 */
@Service
public abstract class NamedServiceImpl<E extends NamedEntity, R extends NamedRepository<E>> extends SimpleServiceImpl<E, R> implements NamedService<E> {

    @Autowired
    private R repository;

    @Override
    public E getByName(String name) {
        return repository.findByName(name);
    }
}
