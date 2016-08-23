package by.lostFinder.controllers;

import by.lostFinder.entities.IdEntity;
import by.lostFinder.services.SimpleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public abstract class CrudController<E extends IdEntity, S extends SimpleService<E>> extends GenericController<S> {

    protected CrudController(S service){
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    protected Page<E> getAll(Pageable pageable){
        return service.getAll(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    protected E getById(@PathVariable("id") String id){
        return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    protected E save(@RequestBody E entity){
        return service.save(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    protected void delete(@PathVariable("id") String id){
        service.delete(id);
    }
}
