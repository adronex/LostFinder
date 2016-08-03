package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Contact extends IdEntity {

    private String value;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;

    public Contact(){}

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
