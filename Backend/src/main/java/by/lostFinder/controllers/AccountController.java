package by.lostFinder.controllers;

import by.lostFinder.entities.Account;
import by.lostFinder.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController extends GenericController<Account, AccountService> {
    @Autowired
    protected AccountController(AccountService service){
        super(service);
    }
}

