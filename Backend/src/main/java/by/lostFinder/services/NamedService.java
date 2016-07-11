package by.lostFinder.services;

public interface NamedService<E> extends SimpleService<E> {
    E getByName(String name);
}
