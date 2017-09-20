package ru.dega.models;

/**
 * User class.
 *
 * @author Denis
 * @since 16.09.2017
 */
public class User {
    /**
     * User id.
     */
    private int id;

    /**
     * User login.
     */
    private String login;

    /**
     * User password.
     */
    private String password;

    /**
     * No args constructor.
     */
    public User() {
    }

    /**
     * Default constructor.
     *
     * @param id       user id
     * @param login    user login
     * @param password user password
     */
    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
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
     * Set user id.
     *
     * @param id new id
     */
    public void setId(int id) {
        this.id = id;
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
     * Set user login.
     *
     * @param login new login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get user password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set user password.
     *
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}