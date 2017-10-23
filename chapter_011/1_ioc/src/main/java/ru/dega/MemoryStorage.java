package ru.dega;

import java.util.ArrayList;
import java.util.List;

/**
 * MemoryStorage class.
 *
 * @author Denis
 * @since 03.10.2017
 */
public class MemoryStorage implements Storage {
    /**
     * Users.
     */
    private List<User> users = new ArrayList<>();

    /**
     * Add user to storage.
     *
     * @param user user
     * @return id of added user
     */
    @Override
    public int add(User user) {
        this.users.add(user);
        return users.size() - 1;
    }

    /**
     * Gets user.
     *
     * @param id user id
     * @return the users
     */
    @Override
    public User getUser(int id) {
        return users.get(id);
    }
}