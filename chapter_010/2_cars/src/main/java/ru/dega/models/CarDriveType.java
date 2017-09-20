package ru.dega.models;

/**
 * CarDriveType class.
 *
 * @author Denis
 * @since 17.09.2017
 */
public class CarDriveType extends CarCategoryAbstract {
    /**
     * Car drive type id.
     */
    private int id;

    /**
     * Car drive type name.
     */
    private String name;

    /**
     * No args constructor.
     */
    public CarDriveType() {
    }

    /**
     * Default constructor.
     *
     * @param id   id
     * @param name name
     */
    public CarDriveType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
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