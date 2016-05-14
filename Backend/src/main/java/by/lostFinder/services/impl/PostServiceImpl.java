package by.lostFinder.services.impl;

import by.lostFinder.entities.Post;
import by.lostFinder.repositories.PostRepository;
import by.lostFinder.services.AccountService;
import by.lostFinder.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostServiceImpl extends SimpleServiceImpl<Post, PostRepository> implements SimpleService<Post> {

    @Autowired
    AccountService accountService;

    @Override
    public Post save(Post entity) {
        entity.setAccount(entity.getAccount());
        return super.save(entity);
    }
}
