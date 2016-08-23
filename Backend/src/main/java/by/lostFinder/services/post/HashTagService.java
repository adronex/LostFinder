package by.lostFinder.services.post;

import by.lostFinder.entities.post.HashTag;
import by.lostFinder.services.NamedService;
import org.springframework.data.domain.Page;

public interface HashTagService extends NamedService<HashTag> {

    Page<HashTag> getSearchResults(String string, int page, int size);
}
