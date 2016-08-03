package by.lostFinder.dto.post;

import by.lostFinder.entities.Post;
import by.lostFinder.entities.PostType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

public class PostDto {
    private String id;
    @NotNull
    private String description;
    @NotNull
    private int lifetime;
    @NotNull
    private PostType postType;
    @NotNull
    private String title;
    @NotNull
    private LocationAreaDto locationArea;
    @NotNull
    private List<LocationDto> locations;
    @NotNull
    private List<HashTagDto> hashTags;

    @JsonIgnore
    public Post getNewEntity(){
        return new Post(description, lifetime, postType, title, locationArea.getNewAreaEntity());
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public int getLifetime() {
        return lifetime;
    }

    public PostType getPostType() {
        return postType;
    }

    public String getTitle() {
        return title;
    }

    public LocationAreaDto getLocationArea() {
        return locationArea;
    }

    public List<LocationDto> getLocations() {
        return locations;
    }

    public List<HashTagDto> getHashTags() {
        return hashTags;
    }
}
