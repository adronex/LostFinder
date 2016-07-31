package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "hashtag")
public class HashTag extends IdEntity {

    protected String name;

    public HashTag(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
