package by.lostFinder.entities.account;

import by.lostFinder.entities.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class AccountDetail extends IdEntity {

    private String name;

    private String description;

    @JsonIgnore
    @OneToOne(mappedBy = "details")
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
