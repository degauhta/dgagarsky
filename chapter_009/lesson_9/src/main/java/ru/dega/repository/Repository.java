package ru.dega.repository;

import java.util.List;

/**
 * Repository class.
 *
 * @author Denis
 * @since 04.09.2017
 * @param <T> type
 */
public interface Repository<T> {
    /**
     * Execute query.
     *
     * @param specification specification sql query
     * @return result of query, format - [{"key":"value"}]
     */
    List<T> query(Specification specification);
}