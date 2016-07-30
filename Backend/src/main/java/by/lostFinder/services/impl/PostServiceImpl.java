package by.lostFinder.services.impl;

import by.lostFinder.entities.Post;
import by.lostFinder.repositories.PostRepository;
import by.lostFinder.services.AccountService;
import by.lostFinder.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostServiceImpl extends SimpleServiceImpl<Post, PostRepository> implements PostService {

    @Autowired
    AccountService accountService;

    @Override
    public Post save(Post entity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        entity.setAccount(accountService.findByAuthUsername(authentication.getName()));
        entity.setDate(LocalDate.now());
        return super.save(entity);
    }

    @Override
    public List<Post> getSearchResult(String title, String description, int page, int size) {
        return repository.findByTitleOrDescriptionContainingIgnoreCase(title, description, new PageRequest(page, size)).getContent();
    }
}
