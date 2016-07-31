package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.LocationEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "location_area")
public class LocationArea extends LocationEntity {

    @Column(name = "radius")
    private int radius;

    public LocationArea(){
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
