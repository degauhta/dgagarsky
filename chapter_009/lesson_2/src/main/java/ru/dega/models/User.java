package ru.dega.models;

import java.time.LocalDateTime;

/**
 * User class.
 *
 * @author Denis
 * @since 04.08.2017
 */
public class User {
    /**
     * User name.
     */
    private String name;

    /**
     * User login.
     */
    private String login;

    /**
     * User email.
     */
    private String email;

    /**
     * Date when user created.
     */
    private LocalDateTime createDate;

    /**
     * Default constructor.
     * @param name user name
     * @param login user login
     * @param email user email
     * @param createDate date when user created
     */
    public User(String name, String login, String email, LocalDateTime createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    /**
     * Get user name.
     *
     * @return name
     */
    public String getName() {
        return name;
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
     * Get user email.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get time when user created.
     *
     * @return createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }
}
