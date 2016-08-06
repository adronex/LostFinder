package by.lostFinder.utils;

import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created on 21.06.2016;
 *
 * @author p.sinitskiy (adronex303@gmail.com);
 * @since 1.0.
 */
public final class ExceptionUtils {

    private ExceptionUtils() {
    }

    public static void throwAccessDenied(Class<?> clazz) {

        String errorMessage = "Access denied!";
        LoggerFactory.getLogger(clazz).error(errorMessage);
        throw new AccessDeniedException(errorMessage);
    }

    public static void throwAccessDenied(Class<?> clazz, String message) {

        LoggerFactory.getLogger(clazz).error(message);
        throw new AccessDeniedException(message);
    }

    public static void throwNotAuthenticatedException(Class<?> clazz) {

        String errorMessage = "Not authenticated!";
        LoggerFactory.getLogger(clazz).error(errorMessage);
        throw new AuthenticationCredentialsNotFoundException(errorMessage);
    }

    public static void throwUsernameNotFoundException(Class<?> clazz) {

        String errorMessage = "Username not found!";
        LoggerFactory.getLogger(clazz).error(errorMessage);
        throw new UsernameNotFoundException(errorMessage);
    }

    public static void throwNotFoundException(Class<?> clazz, String entity) {

        String errorMessage = entity + " not found!";
        LoggerFactory.getLogger(clazz).error(errorMessage);
        throw new UsernameNotFoundException(errorMessage);
    }
}
