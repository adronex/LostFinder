package by.lostFinder.controllers;

import by.lostFinder.entities.Post;
import by.lostFinder.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController extends GenericController<Post, PostService> {

    @Autowired
    protected PostController(PostService service){
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    protected List<Post> getSearchResult(@RequestParam("title") String title,
                                         @RequestParam("description") String description,
                                         @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                         @RequestParam(value = "size", required = false, defaultValue = "3") int size) {
        return service.getSearchResult(title, description, page, size);
    }

}
