package by.lostFinder.entities.superEntity;

import javax.persistence.MappedSuperclass;

/**
 * Created by М on 27.02.2016.
 */
@MappedSuperclass
public class NamedEntity extends IdEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
