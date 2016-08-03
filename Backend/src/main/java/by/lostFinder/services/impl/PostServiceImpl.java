package by.lostFinder.services.impl;

import by.lostFinder.entities.HashTag;
import by.lostFinder.entities.Post;
import by.lostFinder.repositories.AccountRepository;
import by.lostFinder.repositories.HashTagRepository;
import by.lostFinder.repositories.PostRepository;
import by.lostFinder.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl extends SimpleServiceImpl<Post, PostRepository> implements PostService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    HashTagRepository hashTagRepository;

    @Override
    public Post save(Post entity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        entity.setAccount(accountRepository.findByEmailIgnoreCase(authentication.getName()));
        entity.setDate(LocalDate.now());
        entity.getLocations().forEach(e -> e.setPost(entity));
        entity.getContacts().forEach(e -> e.setPost(entity));
        entity.setHashTags(checkHashTags(entity.getHashTags()));
//        entity.getPostType().setPosts();
        return super.save(entity);
    }

    @Override
    public List<Post> getSearchResult(String title, String description, int page, int size) {
        return repository.findByTitleOrDescriptionContainingIgnoreCase(title, description, new PageRequest(page, size)).getContent();
    }

    private List<HashTag> checkHashTags(List<HashTag> hashTags) {
        List<HashTag> tags = new ArrayList<>();
        hashTags.forEach(h -> {
            if (hashTagRepository.findByName(h.getName()) == null) {
                hashTagRepository.save(h);
            }
            tags.add(hashTagRepository.findByName(h.getName()));
        });
        return tags;
    }
}
