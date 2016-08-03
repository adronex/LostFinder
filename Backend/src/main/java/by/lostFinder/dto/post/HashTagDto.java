package by.lostFinder.dto.post;

import javax.validation.constraints.NotNull;

public class HashTagDto {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }
}
