package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by М on 27.02.2016.
 */
@Entity
@Table(name = "contact_type")
public class ContactType extends NamedEntity{

    public ContactType(String name){
        super(name);
    }

    public ContactType(){}
}
