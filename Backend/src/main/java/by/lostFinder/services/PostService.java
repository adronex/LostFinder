package by.lostFinder.services;

import by.lostFinder.entities.Post;

import java.util.List;

public interface PostService extends SimpleService<Post> {

    List<Post> getSearchResult(String title, String description, int page, int size);
}
