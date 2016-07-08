package by.lostFinder.controllers;

import by.lostFinder.entities.HashTag;
import by.lostFinder.services.NamedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by М on 15.03.2016.
 */
@RestController
@RequestMapping("/api/dictionaries/hashTags")
public class HashTagController extends GenericController<HashTag, NamedService<HashTag>> {
    @Autowired
    protected HashTagController(@Qualifier("hashTagService") NamedService<HashTag> service){
        super(service);
    }
}
