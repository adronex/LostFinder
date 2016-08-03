package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hashtag")
public class HashTag extends IdEntity {

    protected String name;

    public HashTag(){

    }

    public HashTag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
