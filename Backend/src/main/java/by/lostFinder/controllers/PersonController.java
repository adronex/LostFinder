package by.lostFinder.controllers;

import by.lostFinder.entities.Person;
import by.lostFinder.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by М on 15.03.2016.
 */
@RestController
@RequestMapping("/persons")
public class PersonController extends GenericController<Person, SimpleService<Person>> {
    @Autowired
    protected PersonController(@Qualifier("personService") SimpleService<Person> service){
        super(service);
    }
}
