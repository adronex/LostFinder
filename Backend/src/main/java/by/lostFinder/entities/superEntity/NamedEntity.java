package by.lostFinder.entities.superEntity;

import javax.persistence.*;

/**
 * Created by М on 27.02.2016.
 */
@MappedSuperclass
public class NamedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name")
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
