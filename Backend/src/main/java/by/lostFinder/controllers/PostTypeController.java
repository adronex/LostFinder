package by.lostFinder.controllers;

import by.lostFinder.entities.PostType;
import by.lostFinder.services.NamedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dictionaries/postTypes")
public class PostTypeController extends GenericController<PostType, NamedService<PostType>> {
    @Autowired
    protected PostTypeController(@Qualifier("postTypeService") NamedService<PostType> service){super(service);}
}
