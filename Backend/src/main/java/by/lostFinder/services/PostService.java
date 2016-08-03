package by.lostFinder.services;

import by.lostFinder.dto.post.PostDto;
import by.lostFinder.entities.Post;
import org.springframework.data.domain.Page;

public interface PostService extends SimpleService<Post> {

    void save(PostDto dto);

    Page<Post> getSearchResult(String title, String description, int page, int size);
}
