package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by М on 26.02.2016.
 */
@Entity
@Table(name = "city")
public class City extends NamedEntity {

    public City(String name){
        super(name);
    }

    public City(){}
}
