package by.lostFinder.services.impl;

import by.lostFinder.entities.Account;
import by.lostFinder.repositories.AccountRepository;
import by.lostFinder.services.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends SimpleServiceImpl<Account, AccountRepository> implements AccountService {

    @Override
    public Account save(Account entity) {
        entity.getAccountDetail().setAccount(entity);
        return repository.saveAndFlush(entity);
    }

    @Override
    public Account getAccountByLogin(String login) {
        return repository.findByLoginIgnoreCase(login);
    }

    @Override
    public Account getAccountByEmail(String email) {
        return repository.findByEmailIgnoreCase(email);
    }
}
