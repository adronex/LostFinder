package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.IdEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by М on 07.03.2016.
 */
@Entity
@Table(name = "person")
public class Person extends IdEntity {

//    @OneToOne(mappedBy = "person", fetch = FetchType.EAGER)
//    private Account account;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_city")
    private City city;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private Set<Contact> contacts;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;


    public Person(String first_name, String last_name){
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Person(){}

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
}
