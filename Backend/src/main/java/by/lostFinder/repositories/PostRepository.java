package by.lostFinder.repositories;

import by.lostFinder.entities.Post;
import by.lostFinder.repositories.superRepositories.SimpleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepository extends SimpleRepository<Post> {

    Page<Post> findByTitleOrDescriptionContainingIgnoreCase(String title, String description, Pageable pageable);
}
