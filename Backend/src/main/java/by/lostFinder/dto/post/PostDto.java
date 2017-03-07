package by.lostFinder.dto.post;

import by.lostFinder.entities.post.Post;
import by.lostFinder.entities.post.PostType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
    @NotEmpty
    private List<LocationDto> locations = new ArrayList<>();
    @NotEmpty
    private List<HashTagDto> hashTags = new ArrayList<>();
    @NotEmpty
    private List<ContactDto> contacts = new ArrayList<>();

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

    public List<ContactDto> getContacts() {
        return contacts;
    }
}
