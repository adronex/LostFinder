package by.lostFinder.services;

import by.lostFinder.entities.superEntity.NamedEntity;

/**
 * Created by М on 14.03.2016.
 */
public interface NamedService<E extends NamedEntity> extends SimpleService<E> {
    E getByName(String name);
}
