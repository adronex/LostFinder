package by.lostFinder.controllers;

import by.lostFinder.entities.Contact;
import by.lostFinder.services.NamedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/accounts/accountsDetail/contacts")
public class ContactController extends GenericController<Contact, NamedService<Contact>> {
    @Autowired
    protected ContactController(@Qualifier("contactService") NamedService<Contact> service){
        super(service);
    }
}
