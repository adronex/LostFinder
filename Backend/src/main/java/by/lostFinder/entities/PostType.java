package by.lostFinder.entities;

import javax.persistence.*;

/**
 * Created by М on 08.03.2016.
 */

@Entity
@Table(name = "post_type")
public class PostType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected long id;

    @Column(name = "name")
    protected String name;

    public PostType(String name){
        this.name = name;
    }

    public PostType(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
