package by.lostFinder.dto.post;

import by.lostFinder.entities.post.LocationArea;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

public class LocationAreaDto extends LocationDto {

    @NotNull
    private int radius;

    @JsonIgnore
    public LocationArea getNewAreaEntity(){
        return new LocationArea(radius, address, lat, lng);
    }

    public int getRadius() {
        return radius;
    }
}
