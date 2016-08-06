package by.lostFinder.dto.post;

import by.lostFinder.entities.post.Contact;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

public class ContactDto {

    @NotNull
    private String value;

    @JsonIgnore
    public Contact getNewEntity (){
        return new Contact(value);
    }

    public String getValue() {
        return value;
    }
}
