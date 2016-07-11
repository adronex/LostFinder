package by.lostFinder.repositories.superRepositories;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface NamedRepository<E> extends SimpleRepository<E> {
    E findByName(String name);
}
