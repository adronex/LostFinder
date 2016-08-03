package by.lostFinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SimpleRepository<E> extends JpaRepository<E, String> {
}
