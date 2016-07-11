package by.lostFinder.repositories;

import by.lostFinder.entities.Account;
import by.lostFinder.entities.OAuthType;
import by.lostFinder.repositories.superRepositories.SimpleRepository;


public interface AccountRepository extends SimpleRepository<Account> {

    Account findByEmailIgnoreCaseAndOauthType(String email, OAuthType oAuthType);

    Account findByOauthIdAndOauthType(String oauthId, OAuthType oauthType);
}
