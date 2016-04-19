package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by М on 08.03.2016.
 */

@Entity
@Table(name = "post_type")
public class PostType extends NamedEntity {

    public PostType(String name){
        super(name);
    }

    public PostType(){}
}
