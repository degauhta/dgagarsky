package ru.dega.models;

/**
 * User class.
 *
 * @author Denis
 * @since 30.08.2017
 */
public class User {
    /**
     * ID.
     */
    private int id;

    /**
     * Name.
     */
    private String login;

    /**
     * Role id.
     */
    private int roleId;

    /**
     * Default constructor.
     *
     * @param id     id
     * @param name   name
     * @param roleId role id
     */
    public User(int id, String name, int roleId) {
        this.id = id;
        this.login = name;
        this.roleId = roleId;
    }

    /**
     * Get user id.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Get user login.
     *
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Get role id.
     *
     * @return role id
     */
    public int getRoleId() {
        return roleId;
    }
}