package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "post_type")
public class PostType extends IdEntity {

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "postType")
    private List<Post> posts;

    public PostType(){}

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
