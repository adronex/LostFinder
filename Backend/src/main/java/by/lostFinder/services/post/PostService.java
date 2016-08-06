package by.lostFinder.services.post;

import by.lostFinder.dto.post.PostDto;
import by.lostFinder.entities.post.Post;
import by.lostFinder.services.SimpleService;
import org.springframework.data.domain.Page;

public interface PostService extends SimpleService<Post> {

    void save(PostDto dto);

    Page<Post> getSearchResult(String title, String description, int page, int size);
}
