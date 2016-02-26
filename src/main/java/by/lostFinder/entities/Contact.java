package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.IdEntity;

import javax.persistence.Column;

/**
 * Created by М on 27.02.2016.
 */
public class Contact extends IdEntity {

    @Column
    private ContactType contactType;

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }
}
