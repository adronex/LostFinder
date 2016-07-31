package by.lostFinder.services.impl;

import by.lostFinder.entities.Account;
import by.lostFinder.entities.AccountDetail;
import by.lostFinder.entities.OAuthType;
import by.lostFinder.repositories.AccountRepository;
import by.lostFinder.services.AccountService;
import by.lostFinder.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends SimpleServiceImpl<Account, AccountRepository> implements AccountService {

    @Autowired
    SimpleService<AccountDetail> accountDetailService;

    @Override
    public Account save(Account entity) {
        entity.getAccountDetail().setAccount(entity);
        if (entity.getPosts() != null) entity.getPosts().forEach(post->post.setAccount(entity));
        return super.save(entity);
    }
}
