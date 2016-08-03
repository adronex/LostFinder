package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.LocationEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Location extends LocationEntity {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Location() {
    }

    public Location(String address, float lat, float lng) {
        super(address, lat, lng);
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
