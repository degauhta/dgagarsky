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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        if (login != null ? !login.equals(user.login) : user.login != null) {
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }
        return createDate != null ? createDate.equals(user.createDate) : user.createDate == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}