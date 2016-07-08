package by.lostFinder.controllers;

import by.lostFinder.entities.Post;
import by.lostFinder.services.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController extends GenericController<Post, SimpleService<Post>> {
    @Autowired
    protected PostController(@Qualifier("postService") SimpleService<Post> service){
        super(service);
    }
}
