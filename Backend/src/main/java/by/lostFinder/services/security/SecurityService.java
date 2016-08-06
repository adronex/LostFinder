package by.lostFinder.services.security;

import by.lostFinder.entities.account.Account;
import by.lostFinder.services.SimpleService;

public interface SecurityService extends SimpleService<Account> {

    Account getCurrentAccount();
}
