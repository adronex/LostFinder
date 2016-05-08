package by.lostFinder.services.impl;

import by.lostFinder.entities.Person;
import by.lostFinder.repositories.PersonRepository;
import by.lostFinder.services.PersonService;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends SimpleServiceImpl<Person, PersonRepository> implements PersonService {
}
