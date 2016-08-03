package by.lostFinder.services;

import by.lostFinder.dto.post.HashTagDto;
import by.lostFinder.dto.post.PostDto;
import by.lostFinder.entities.*;
import by.lostFinder.repositories.account.AccountRepository;
import by.lostFinder.repositories.post.ContactRepository;
import by.lostFinder.repositories.post.HashTagRepository;
import by.lostFinder.repositories.post.LocationRepository;
import by.lostFinder.repositories.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    @Override
    @Transactional
    public Post save(Post entity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        entity.setAccount(accountRepository.findByEmailIgnoreCase(authentication.getName()));
        entity.setDate(LocalDate.now());
        List<Location> locations = new ArrayList<>();
        locations.addAll(entity.getLocations());
        entity.getLocations().clear();
        List<Contact> contacts = new ArrayList<>();
        contacts.addAll(entity.getContacts());
        entity.getContacts().clear();
     //   checkHashTags(entity.getHashTags());
        super.save(entity);
        entity.setLocations(locations);
        entity.setContacts(contacts);
        entity.getLocations().forEach(e -> e.setPost(entity));
        entity.getContacts().forEach(e -> e.setPost(entity));
        super.save(entity);
        return entity;
    }

    @Override
    @Transactional
    public void save(PostDto dto) {
        Post post = dto.getNewEntity();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        post.setAccount(accountRepository.findByEmailIgnoreCase(authentication.getName()));
        post.setHashTags(getUpdatedHashTags(dto.getHashTags()));
        dto.getLocations().forEach(l -> post.getLocations().add(l.getNewEntity()));
        post.getLocations().forEach(l -> l.setPost(post));
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
