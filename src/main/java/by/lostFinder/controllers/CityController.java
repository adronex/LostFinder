package by.lostFinder.controllers;

import by.lostFinder.entities.City;
import by.lostFinder.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by М on 26.02.2016.
 */

@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    CityRepository cityRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<City> getCities()
    {
        List<City> result = new ArrayList<>();
        cityRepository.findAll().forEach(result::add);
        return result;
    }
}
