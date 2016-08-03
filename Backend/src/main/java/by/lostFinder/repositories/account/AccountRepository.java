package by.lostFinder.repositories.account;

import by.lostFinder.entities.Account;
import by.lostFinder.repositories.SimpleRepository;


public interface AccountRepository extends SimpleRepository<Account> {

    Account findByEmailIgnoreCase(String email);
}
