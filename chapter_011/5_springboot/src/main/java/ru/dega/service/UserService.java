package ru.dega.service;

import ru.dega.domain.User;

import java.util.List;

/**
 * UserService class.
 *
 * @author Denis
 * @since 05.01.2018
 */
public interface UserService {
    /**
     * Get all users.
     *
     * @return list of users
     */
    List<User> getAll();

    /**
     * Find user by id.
     *
     * @param id id
     * @return user
     */
    User findById(int id);
}