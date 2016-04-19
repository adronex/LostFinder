package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.NamedEntity;

import javax.persistence.*;

/**
 * Created by М on 27.02.2016.
 */
@Entity
@Table(name = "contact")
public class Contact extends NamedEntity {

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contact_type")
    private ContactType contactType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_person")
    private Person person;

    public Contact(String name){
        super(name);
    }

    public Contact(){}

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }
}
