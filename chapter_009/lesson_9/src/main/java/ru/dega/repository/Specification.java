package ru.dega.repository;

/**
 * Specification class.
 *
 * @author Denis
 * @since 04.09.2017
 */
public interface Specification {
    /**
     * Sql query.
     *
     * @return SQL statement to be sent to the database
     */
    String toSqlQuery();
}
