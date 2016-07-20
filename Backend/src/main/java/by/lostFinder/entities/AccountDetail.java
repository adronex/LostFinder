package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "account_detail")
public class AccountDetail extends IdEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "accountDetail")
    private List<Contact> contacts;

    @JsonIgnore
    @OneToOne(mappedBy = "accountDetail")
    private Account account;

    public AccountDetail(){}

    public AccountDetail(String name) {
        this.name = name;
    }


    public AccountDetail(String lastName, String firstName) {
        this.name = lastName + " " + firstName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
