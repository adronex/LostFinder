package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Post extends IdEntity {

    @ManyToOne
    @JoinColumn(name = "post_type_id")
    private PostType postType;

    private String title;

    private String description;

    private LocalDate date;

    private int lifetime;

    @OneToMany(mappedBy = "post")
    private List<Contact> contacts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "POST_HASHTAG", joinColumns = @JoinColumn(name = "ID_POST"),
            inverseJoinColumns = @JoinColumn(name = "ID_HASHTAG"))
    private List<HashTag> hashTags;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<Location> locations;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_area")
    private LocationArea locationArea;

    public Post(){}

    public List<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(List<HashTag> hashTags) {
        this.hashTags = hashTags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public LocationArea getLocationArea() {
        return locationArea;
    }

    public void setLocationArea(LocationArea locationArea) {
        this.locationArea = locationArea;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
