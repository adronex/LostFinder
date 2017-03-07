package by.lostFinder.repositories.post;

import by.lostFinder.entities.post.Post;
import by.lostFinder.repositories.SimpleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

public interface PostRepository extends SimpleRepository<Post> {

    Page<Post> findByTitleOrDescriptionContainingIgnoreCase(String title, String description, Pageable pageable);
}
