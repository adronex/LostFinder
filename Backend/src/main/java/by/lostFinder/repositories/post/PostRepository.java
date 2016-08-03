package by.lostFinder.repositories.post;

import by.lostFinder.entities.Post;
import by.lostFinder.repositories.SimpleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepository extends SimpleRepository<Post> {

    Page<Post> findByTitleOrDescriptionContainingIgnoreCase(String title, String description, Pageable pageable);
}
