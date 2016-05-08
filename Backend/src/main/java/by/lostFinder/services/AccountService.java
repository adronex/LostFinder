package by.lostFinder.services;

import by.lostFinder.entities.Account;

public interface AccountService extends SimpleService<Account> {

    public Account getAccountByLogin(String login);

    public Account getAccountByEmail(String email);
}
