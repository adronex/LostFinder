package by.lostFinder.services;

import by.lostFinder.repositories.SimpleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public abstract class SimpleServiceImpl<E, R extends SimpleRepository<E>> implements SimpleService<E>{

    @Autowired
    protected R repository;

    @Override
    public E save(E entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public E getById(String id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public Page<E> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
