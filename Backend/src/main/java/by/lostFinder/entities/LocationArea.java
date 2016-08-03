package by.lostFinder.entities;

import by.lostFinder.entities.superEntity.LocationEntity;

import javax.persistence.Entity;

@Entity
public class LocationArea extends LocationEntity {

    private int radius;

    public LocationArea(){
    }

    public LocationArea(int radius, String address, float lat, float lng) {
        super(address, lat, lng);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
