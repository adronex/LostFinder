package by.lostFinder.entities.post;

import by.lostFinder.entities.LocationEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Location extends LocationEntity {

    @JsonIgnore
    @ManyToOne
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
