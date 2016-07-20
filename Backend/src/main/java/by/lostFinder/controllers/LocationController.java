package by.lostFinder.controllers;

import by.lostFinder.entities.Location;
import by.lostFinder.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts/locations")
public class LocationController extends GenericController<Location, SimpleService<Location>> {
    @Autowired
    protected LocationController(@Qualifier("locationService") SimpleService<Location> service){
        super(service);
    }
}
