package by.lostFinder.services.impl;

import by.lostFinder.entities.Post;
import by.lostFinder.repositories.PostRepository;
import by.lostFinder.services.AccountService;
import by.lostFinder.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class PostServiceImpl extends SimpleServiceImpl<Post, PostRepository> implements SimpleService<Post> {

    @Autowired
    AccountService accountService;

    @Override
    public Post save(Post entity) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        entity.setAccount(accountService.getAccountByLogin(authentication.getName()));
        return super.save(entity);
    }
}
