package by.lostFinder.controllers;

import by.lostFinder.entities.post.HashTag;
import by.lostFinder.services.post.HashTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hashTags")
public class HashTagController extends CrudController<HashTag, HashTagService>{

    @Autowired
    protected HashTagController(HashTagService service) {
        super(service);
    }

}
