package ru.dega.dao;

import ru.dega.models.User;

import java.util.List;

/**
 * UserDao class.
 *
 * @author Denis
 * @since 30.08.2017
 */
public interface UserDao {
    /**
     * Get all users.
     *
     * @return list of users
     */
    List<User> getAll();

    /**
     * Create new user.
     *
     * @param user    user obj
     * @param musicID music types
     * @return id of new element
     */
    int createUser(User user, String[] musicID);

    /**
     * Get user by name.
     *
     * @param name name
     * @return user obj
     */
    User getByName(String name);

    /**
     * Update user.
     *
     * @param user     user
     * @param musicID  music types
     * @param sameUser true if login not change
     * @return true if update successfully
     */
    boolean update(User user, String[] musicID, boolean sameUser);

    /**
     * Delete user by id.
     *
     * @param id user id
     * @return true if delete successfully
     */
    boolean deleteByID(int id);
}