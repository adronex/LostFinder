package by.lostFinder.entities;

import javax.persistence.*;

/**
 * Created by М on 08.03.2016.
 */
@Entity
@Table(name = "post")
public class Post {

    //todo: ??
//    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "posts")
//    private Set<Account> accounts;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected long id;

//    @ManyToMany
//    @JoinTable(name = "POST_HASHTAG", joinColumns = @JoinColumn(name = "ID_POST"),
//                                      inverseJoinColumns = @JoinColumn(name = "ID_HASHTAG"))
//    private Set<HashTag> hashTags;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_post_type")
    private PostType postType;

    @Column(name = "description")
    private String description;

    public Post(String description){
        this.description = description;
    }

    public Post(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public Set<HashTag> getHashTags() {
//        return hashTags;
//    }
//
//    public void setHashTags(Set<HashTag> hashTags) {
//        this.hashTags = hashTags;
//    }

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
