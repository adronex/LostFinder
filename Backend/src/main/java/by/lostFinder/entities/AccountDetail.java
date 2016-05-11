package by.lostFinder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by М on 07.03.2016.
 */
@Entity
@Table(name = "account_detail")
public class AccountDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private long id;

    @JsonIgnore
    @MapsId
    @OneToOne(mappedBy = "accountDetail")
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "accountDetail")
    private Set<Contact> contacts;

    @Column(name = "city")
    private String city;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;


    public AccountDetail(String first_name, String last_name){
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public AccountDetail(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
