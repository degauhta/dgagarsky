package ru.dega.models;

/**
 * CarTransmission class.
 *
 * @author Denis
 * @since 17.09.2017
 */
public class CarTransmission extends CarCategoryAbstract {
    /**
     * Car transmission id.
     */
    private int id;

    /**
     * Car transmission name.
     */
    private String name;

    /**
     * No args constructor.
     */
    public CarTransmission() {
    }

    /**
     * Default constructor.
     *
     * @param id   id
     * @param name name
     */
    public CarTransmission(int id, String name) {
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