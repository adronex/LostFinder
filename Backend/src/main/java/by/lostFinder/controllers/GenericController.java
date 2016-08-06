package by.lostFinder.controllers;

import by.lostFinder.dto.JsonExceptionMessage;
import javassist.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created on 05.03.2016;
 *
 * @author p.sinitskiy (adronex303@gmail.com);
 * @since 1.0.
 */
public abstract class GenericController<S> {

    private static final String ERROR_MESSAGE_TITLE = "Error";

    protected S service;

    protected GenericController(S service) {
        this.service = service;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public JsonExceptionMessage handleException() {
        return new JsonExceptionMessage(ERROR_MESSAGE_TITLE, "Not found!");
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonExceptionMessage handleException(NotFoundException ex) {
        return new JsonExceptionMessage(ERROR_MESSAGE_TITLE, ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonExceptionMessage handleException(AccessDeniedException ex) {
        return new JsonExceptionMessage(ERROR_MESSAGE_TITLE, ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonExceptionMessage handleException(DataIntegrityViolationException ex) {
        return new JsonExceptionMessage(ERROR_MESSAGE_TITLE, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonExceptionMessage handleException(IllegalArgumentException ex) {
        return new JsonExceptionMessage(ERROR_MESSAGE_TITLE, ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public JsonExceptionMessage handleException(UsernameNotFoundException ex) {
        return new JsonExceptionMessage(ERROR_MESSAGE_TITLE, ex.getMessage());
    }
}
