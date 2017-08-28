package ru.dega.models;

import java.time.LocalDateTime;

/**
 * User class.
 *
 * @author Denis
 * @since 11.08.2017
 */
public class User {
    /**
     * User login.
     */
    private String login;

    /**
     * Password.
     */
    private String password;

    /**
     * User name.
     */
    private String name;


    /**
     * User email.
     */
    private String email;

    /**
     * Date when user created.
     */
    private LocalDateTime createDate;

    /**
     * Role.
     */
    private UserRole role;

    /**
     * Default constructor.
     *
     * @param login      user login
     * @param password   user password
     * @param name       user name
     * @param email      user email
     * @param createDate create date
     * @param role       user role
     */
    public User(String login, String password, String name, String email, LocalDateTime createDate, UserRole role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createDate = createDate;
        this.role = role;
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

    /**
     * Get password.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get role.
     *
     * @return role
     */
    public UserRole getRole() {
        return role;
    }
}