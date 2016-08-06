package by.lostFinder.repositories.account;

import by.lostFinder.entities.account.Account;
import by.lostFinder.repositories.SimpleRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends SimpleRepository<Account> {

    Account findByEmailIgnoreCase(String email);
}
