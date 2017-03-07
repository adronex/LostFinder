package by.lostFinder.controllers;

import by.lostFinder.entities.IdEntity;
import by.lostFinder.services.SimpleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

public abstract class CrudController<E extends IdEntity, S extends SimpleService<E>> extends GenericController<S> {

    protected CrudController(S service){
        super(service);
    }

    @GetMapping
    public Page<E> getAll(Pageable pageable){
        return service.getAll(pageable);
    }

    @GetMapping(value = "/{id}")
    public E getById(@PathVariable("id") String id){
        return service.getById(id);
    }

    @PutMapping
    public E save(@RequestBody E entity){
        return service.save(entity);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") String id){
        service.delete(id);
    }
}
