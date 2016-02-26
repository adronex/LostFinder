package by.lostFinder.repositories;

/**
 * Created by operb_000 on 16.10.2015.
 */
import by.lostFinder.entities.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByLogin(String login);
}
