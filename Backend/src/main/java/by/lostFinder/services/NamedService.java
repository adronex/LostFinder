package by.lostFinder.services;

/**
 * Created by М on 14.03.2016.
 */
public interface NamedService<E> extends SimpleService<E> {
    E getByName(String name);
}
