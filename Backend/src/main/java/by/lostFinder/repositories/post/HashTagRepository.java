package by.lostFinder.repositories.post;

import by.lostFinder.entities.post.HashTag;
import by.lostFinder.repositories.NamedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface HashTagRepository extends NamedRepository<HashTag> {

    Page<HashTag> findByNameLike(String string, Pageable pageable);

}
