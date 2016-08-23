package by.lostFinder.services.post;

import by.lostFinder.entities.post.HashTag;
import by.lostFinder.repositories.post.HashTagRepository;
import by.lostFinder.services.NamedServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class HashTagServiceImpl extends NamedServiceImpl<HashTag, HashTagRepository> implements HashTagService {

    @Override
    public Page<HashTag> getSearchResults(String string, int page, int size) {
        return repository.findByNameLike(string, new PageRequest(page, size));
    }
}
