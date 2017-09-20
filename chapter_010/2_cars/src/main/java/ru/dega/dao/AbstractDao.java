package ru.dega.dao;

import java.util.List;

/**
 * AbstractDao class.
 *
 * @param <E> the type of elements
 * @author Denis
 * @since 17.09.2017
 */
public interface AbstractDao<E> {
    /**
     * Get all abstract items.
     *
     * @param query hql string
     * @return list
     */
    List<E> getAll(String query);
}