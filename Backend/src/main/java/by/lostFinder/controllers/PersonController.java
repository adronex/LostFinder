package by.lostFinder.controllers;

import by.lostFinder.entities.Person;
import by.lostFinder.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by М on 15.03.2016.
 */
@RestController
@RequestMapping("/persons")
public class PersonController extends GenericController<Person, PersonService> {
    @Autowired
    protected PersonController(PersonService service){
        super(service);
    }
}
