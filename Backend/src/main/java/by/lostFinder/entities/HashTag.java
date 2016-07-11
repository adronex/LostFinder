package by.lostFinder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "hashtag")
public class HashTag extends IdEntity implements Serializable {

    @Column(name = "name")
    protected String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "hashTags")
    private List<Post> posts;

    public HashTag(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
