package by.lostFinder.entities.account;

import by.lostFinder.entities.IdEntity;
import by.lostFinder.entities.post.Post;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "account")
public class Account extends IdEntity implements UserDetails {

    private String password;

    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Post> posts = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AccountDetail details;

    private String oauthId;

    public Account() {
    }

    public
    Account(String email, AccountDetail detail, String oauthId) {
        this.oauthId = oauthId;
        this.email = email;
        this.details = detail;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public AccountDetail getDetails() {
        return details;
    }

    public void setDetails(AccountDetail details) {
        this.details = details;
    }

    public String getOauthId() {
        return oauthId;
    }

    // SPRING SECURITY IMPLEMENTATION
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
