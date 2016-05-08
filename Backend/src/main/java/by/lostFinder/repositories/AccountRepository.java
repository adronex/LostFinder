package by.lostFinder.repositories;

import by.lostFinder.entities.Account;
import by.lostFinder.repositories.superRepositories.SimpleRepository;


public interface AccountRepository extends SimpleRepository<Account> {

    Account findByLoginIgnoreCase(String login);

    Account findByEmailIgnoreCase(String email);
}
