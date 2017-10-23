package ru.dega;

/**
 * Storage class.
 *
 * @author Denis
 * @since 03.10.2017
 */
interface Storage {
    /**
     * Add user to storage.
     *
     * @param user user
     * @return id of added user
     */
    int add(User user);

    /**
     * Gets user.
     *
     * @param id user id
     * @return the users
     */
    User getUser(int id);
}