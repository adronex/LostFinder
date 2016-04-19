package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by М on 08.03.2016.
 */
@Entity
@Table(name = "hashtag")
public class HashTag extends NamedEntity {

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "hashTags")
    private Set<Post> posts;

    public HashTag(String name){
        super(name);
    }

    public HashTag(){}
}
