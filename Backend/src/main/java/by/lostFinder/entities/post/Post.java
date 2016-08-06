package by.lostFinder.entities.post;

import by.lostFinder.entities.IdEntity;
import by.lostFinder.entities.account.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post extends IdEntity {

    @Enumerated
    private PostType postType;

    private String title;

    private String description;

    private LocalDate date;

    private int lifetime;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    private List<Contact> contacts = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "POST_HASHTAG", joinColumns = @JoinColumn(name = "ID_POST"),
            inverseJoinColumns = @JoinColumn(name = "ID_HASHTAG"))
    private List<HashTag> hashTags = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "post")
    private List<Location> locations = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_AREA")
    private LocationArea locationArea;

    public Post(){
    }

    public Post(String description, int lifetime, PostType postType, String title, LocationArea locationArea) {
        this.description = description;
        this.lifetime = lifetime;
        this.postType = postType;
        this.title = title;
        this.locationArea = locationArea;
        date = LocalDate.now();
    }

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
