package by.lostFinder.controllers;

import by.lostFinder.entities.LocationArea;
import by.lostFinder.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts/locationArea")
public class LocationAreaController extends GenericController<LocationArea, SimpleService<LocationArea>> {
    @Autowired
    protected LocationAreaController(@Qualifier("locationAreaService") SimpleService<LocationArea> service){
        super(service);
    }
}
