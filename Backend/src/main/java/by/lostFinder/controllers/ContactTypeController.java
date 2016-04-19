package by.lostFinder.controllers;

import by.lostFinder.entities.ContactType;
import by.lostFinder.services.NamedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by М on 15.03.2016.
 */
@RestController
@RequestMapping("/contactTypes")
public class ContactTypeController extends GenericController<ContactType, NamedService<ContactType>> {
    @Autowired
    protected ContactTypeController(@Qualifier("contactTypeService") NamedService<ContactType> service){
        super(service);
    }
}
