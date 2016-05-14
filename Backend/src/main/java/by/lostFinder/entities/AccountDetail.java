package by.lostFinder.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by М on 07.03.2016.
 */
@Entity
@Table(name = "account_detail")
public class AccountDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private long accountId;

    //@JsonIgnore
    @JsonBackReference
    @MapsId
    @OneToOne(mappedBy = "accountDetail")
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "accountDetail")
    private List<Contact> contacts;

    @Column(name = "city")
    private String city;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    public AccountDetail(){}

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long AccountId) {
        this.accountId = AccountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
