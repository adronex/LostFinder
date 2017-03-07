package by.lostFinder.entities.post;

import by.lostFinder.entities.IdEntity;
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
    private Post post;

    public Contact(){}

    public Contact(String value){
        this.value = value;
    }

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
