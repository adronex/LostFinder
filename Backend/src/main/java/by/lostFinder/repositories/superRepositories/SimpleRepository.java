package by.lostFinder.repositories.superRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by М on 14.03.2016.
 */
@NoRepositoryBean
public interface SimpleRepository<E> extends JpaRepository<E, Long> {
}
