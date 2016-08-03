package by.lostFinder.dto.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BaseFilterDto {
    @NotNull
    private Integer page = 0;
    @NotNull
    @Min(1)
    @Max(50)
    private Integer size = 25;

    @JsonIgnore
    public PageRequest getPageableObject(){
        return new PageRequest(page, size);
    }
}
