package ru.dega;

/**
 * User class.
 *
 * @author Denis
 * @since 03.10.2017
 */
public class User {
    /**
     * User name.
     */
    private String name;

    /**
     * Instantiates a new User.
     *
     * @param name the name
     */
    User(String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}