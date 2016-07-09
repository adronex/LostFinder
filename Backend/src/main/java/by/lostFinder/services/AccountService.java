package by.lostFinder.services;

import by.lostFinder.entities.Account;

public interface AccountService extends SimpleService<Account> {

    Account getAccountByEmail(String email);
}
