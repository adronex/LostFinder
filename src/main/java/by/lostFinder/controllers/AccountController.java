package by.lostFinder.controllers;

/**
 * Created by operb_000 on 26.02.2016.
 */

import by.lostFinder.entities.Account;
import by.lostFinder.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Account> getUsers()
    {
        List<Account> result = new ArrayList<>();
        accountRepository.findAll().forEach(result::add);
        return result;
    }

}

