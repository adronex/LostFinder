package by.lostFinder.entities.superEntity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class LocationEntity extends IdEntity {

    protected float lat;

    protected float lng;

    protected String address;

    public LocationEntity() {
    }

    public LocationEntity(String address, float lat, float lng) {
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }
}
