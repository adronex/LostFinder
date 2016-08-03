package by.lostFinder.services;

import by.lostFinder.repositories.NamedRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class NamedServiceImpl<E, R extends NamedRepository<E>> extends SimpleServiceImpl<E, R> implements NamedService<E> {

    @Override
    public E getByName(String name) {
        return repository.findByName(name);
    }
}
