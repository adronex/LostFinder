package by.lostFinder.services.post;

import by.lostFinder.dto.post.HashTagDto;
import by.lostFinder.dto.post.PostDto;
import by.lostFinder.entities.post.HashTag;
import by.lostFinder.entities.post.Post;
import by.lostFinder.repositories.account.AccountRepository;
import by.lostFinder.repositories.post.ContactRepository;
import by.lostFinder.repositories.post.HashTagRepository;
import by.lostFinder.repositories.post.LocationRepository;
import by.lostFinder.repositories.post.PostRepository;
import by.lostFinder.services.SimpleServiceImpl;
import by.lostFinder.services.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl extends SimpleServiceImpl<Post, PostRepository> implements PostService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    HashTagRepository hashTagRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    SecurityService securityService;

    @Override
    @Transactional
    public void save(PostDto dto) {
        Post post = dto.getNewEntity();
        post.setAccount(securityService.getCurrentAccount());
        post.setHashTags(getUpdatedHashTags(dto.getHashTags()));
        dto.getLocations().forEach(l -> post.getLocations().add(l.getNewEntity()));
        post.getLocations().forEach(l -> l.setPost(post));
        dto.getContacts().forEach((c -> post.getContacts().add(c.getNewEntity())));
        post.getContacts().forEach(c -> c.setPost(post));
        repository.save(post);
    }

    @Override
    public Page<Post> getSearchResult(String title, String description, int page, int size) {
        return repository.findByTitleOrDescriptionContainingIgnoreCase(title, description, new PageRequest(page, size));
    }

    private List<HashTag> getUpdatedHashTags(List<HashTagDto> hashTagDtoList) {
        Set<HashTag> results = new HashSet<>();
        hashTagDtoList.forEach(h -> {
            HashTag hashTag = hashTagRepository.findByName(h.getName());
            if (hashTag == null) {
                HashTag i = new HashTag(h.getName());
                hashTagRepository.save(i);
                results.add(i);
            } else {
                results.add(hashTag);
            }
        });
        List<HashTag> resultList = new ArrayList<>();
        resultList.addAll(results);
        return resultList;
    }
}
