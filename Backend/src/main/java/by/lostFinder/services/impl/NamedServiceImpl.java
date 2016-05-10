package by.lostFinder.services.impl;

import by.lostFinder.repositories.superRepositories.NamedRepository;
import by.lostFinder.services.NamedService;
import org.springframework.stereotype.Service;

/**
 * Created by М on 14.03.2016.
 */
@Service
public abstract class NamedServiceImpl<E, R extends NamedRepository<E>> extends SimpleServiceImpl<E, R> implements NamedService<E> {

    @Override
    public E getByName(String name) {
        return repository.findByName(name);
    }
}
