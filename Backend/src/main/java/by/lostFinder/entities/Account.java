package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.IdEntity;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "account")
public class Account extends IdEntity{

    @ManyToMany
    @JoinTable(name = "ACCOUNT_POST", joinColumns = @JoinColumn(name = "ID_ACCOUNT"),
                                      inverseJoinColumns = @JoinColumn(name = "ID_POST"))
    private Set<Post> posts;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    //@PrimaryKeyJoinColumn
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    public Account(String login, String password, String email){
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public Account (){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
