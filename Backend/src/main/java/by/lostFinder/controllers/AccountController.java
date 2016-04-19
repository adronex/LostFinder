package by.lostFinder.controllers;

import by.lostFinder.entities.Account;
import by.lostFinder.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController extends GenericController<Account, SimpleService<Account>> {
    @Autowired
    protected AccountController(@Qualifier("accountService") SimpleService<Account> service){
        super(service);
    }

}

