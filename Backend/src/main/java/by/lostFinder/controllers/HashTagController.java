package by.lostFinder.controllers;

import by.lostFinder.entities.HashTag;
import by.lostFinder.services.NamedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dictionaries/hashTags")
public class HashTagController extends GenericController<HashTag, NamedService<HashTag>> {
    @Autowired
    protected HashTagController(@Qualifier("hashTagService") NamedService<HashTag> service){
        super(service);
    }
}
