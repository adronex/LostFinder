package by.lostFinder.entities.superEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by М on 27.02.2016.
 */
@MappedSuperclass
public class NamedEntity extends IdEntity {

    @Column(name = "name")
    protected String name;

    public NamedEntity(String name){
        this.name = name;
    }

    public NamedEntity(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
