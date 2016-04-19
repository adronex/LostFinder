package by.lostFinder.entities.superEntity;

import javax.persistence.*;

@MappedSuperclass
public class IdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected long id;

    public long getId() {
        return id;
    }
}
