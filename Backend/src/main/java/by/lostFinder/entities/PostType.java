package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "post_type")
public class PostType extends IdEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "postType")
    private List<Post> post;

    public PostType(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
  }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }
}
