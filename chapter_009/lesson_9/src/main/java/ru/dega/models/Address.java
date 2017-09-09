package ru.dega.models;

/**
 * Address class.
 *
 * @author Denis
 * @since 01.09.2017
 */
public class Address {
    /**
     * ID.
     */
    private int id;

    /**
     * Representation.
     */
    private String representation;

    /**
     * User id.
     */
    private int userId;

    /**
     * Default constructor.
     *
     * @param id             id
     * @param representation name
     * @param userId        user id
     */
    public Address(int id, String representation, int userId) {
        this.id = id;
        this.representation = representation;
        this.userId = userId;
    }

    /**
     * Get id.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Get representation.
     *
     * @return address representation
     */
    public String getRepresentation() {
        return representation;
    }

    /**
     * Get user id.
     *
     * @return user id
     */
    public int getUserId() {
        return userId;
    }
}