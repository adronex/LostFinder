package by.lostFinder.repositories;

import by.lostFinder.entities.City;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by М on 27.02.2016.
 */
public interface CityRepository extends CrudRepository<City, Long> {
    City findByName(String name);
}
