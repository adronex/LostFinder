package by.lostFinder.controllers;

import by.lostFinder.dto.filter.BaseFilterDto;
import by.lostFinder.dto.post.PostDto;
import by.lostFinder.entities.post.Post;
import by.lostFinder.services.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController extends GenericController<PostService> {

    @Autowired
    protected PostController(PostService service){
        super(service);
    }

    @RequestMapping(method = RequestMethod.PUT)
    protected void save(@RequestBody @Valid PostDto dto) {
        service.save(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private Post getById(@PathVariable("id") String id){
        return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    protected Page<Post> getSearchResult(@RequestBody @Valid BaseFilterDto dto) {
        return service.getAll(dto.getPageableObject());
    }

}
