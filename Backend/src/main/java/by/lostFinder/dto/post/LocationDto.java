package by.lostFinder.dto.post;

import by.lostFinder.entities.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

public class LocationDto {

    @NotNull
    protected String address;
    @NotNull
    protected float lat;
    @NotNull
    protected float lng;

    @JsonIgnore
    public Location getNewEntity(){
        return new Location(address, lat, lng);
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
