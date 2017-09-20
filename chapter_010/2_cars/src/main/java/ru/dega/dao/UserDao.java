package ru.dega.dao;

import ru.dega.models.User;

/**
 * UserDao class.
 *
 * @author Denis
 * @since 16.09.2017
 */
public interface UserDao {
    /**
     * Get user by login.
     *
     * @param login login
     * @return found user
     */
    User getUserByLogin(String login);
}