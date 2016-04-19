package by.lostFinder.controllers;

import by.lostFinder.entities.City;
import by.lostFinder.services.NamedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by М on 26.02.2016.
 */

@RestController
@RequestMapping("/cities")
public class CityController extends GenericController<City, NamedService<City>> {
    @Autowired
    protected CityController(@Qualifier("cityService")NamedService<City> service){
        super(service);
    }
}
