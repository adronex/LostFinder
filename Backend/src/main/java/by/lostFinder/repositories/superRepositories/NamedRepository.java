package by.lostFinder.repositories.superRepositories;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by М on 14.03.2016.
 */
@NoRepositoryBean
public interface NamedRepository<E> extends SimpleRepository<E> {
    E findByName(String name);
}
