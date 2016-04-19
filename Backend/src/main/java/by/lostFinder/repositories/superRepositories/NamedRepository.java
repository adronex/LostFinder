package by.lostFinder.repositories.superRepositories;

import by.lostFinder.entities.superEntity.NamedEntity;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by М on 14.03.2016.
 */
@NoRepositoryBean
public interface NamedRepository<E extends NamedEntity> extends SimpleRepository<E> {
    E findByName(String name);
}
