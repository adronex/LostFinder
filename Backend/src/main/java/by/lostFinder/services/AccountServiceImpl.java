package by.lostFinder.services;

import by.lostFinder.entities.Account;
import by.lostFinder.repositories.account.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends SimpleServiceImpl<Account, AccountRepository> implements AccountService {

    @Override
    public Account save(Account entity) {
        entity.getAccountDetail().setAccount(entity);
        if (entity.getPosts() != null) entity.getPosts().forEach(post->post.setAccount(entity));
        return super.save(entity);
    }
}
