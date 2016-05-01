package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.IdEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by М on 08.03.2016.
 */
@Entity
@Table(name = "post")
public class Post extends IdEntity {

    //todo: ??
//    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "posts")
//    private Set<Account> accounts;

    @ManyToMany
    @JoinTable(name = "POST_HASHTAG", joinColumns = @JoinColumn(name = "ID_POST"),
                                      inverseJoinColumns = @JoinColumn(name = "ID_HASHTAG"))
    private Set<HashTag> hashTags;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_post_type")
    private PostType postType;

    @Column(name = "description")
    private String description;

    public Post(String description){
        this.description = description;
    }

    public Post(){}

    public Set<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(Set<HashTag> hashTags) {
        this.hashTags = hashTags;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
