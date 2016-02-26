package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.IdEntity;

import javax.persistence.*;

/**
 * Created by operb_000 on 09.10.2015.
 */
@Entity
@Table(name = "account")
public class Account extends IdEntity{
    private String login;
    private String password;
    private String email;

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
}
