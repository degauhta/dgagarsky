package ru.dega.models;

/**
 * Car brand class.
 *
 * @author Denis
 * @since 17.09.2017
 */
public class CarBrand extends CarCategoryAbstract {
    /**
     * Car brand id.
     */
    private int id;

    /**
     * Car brand name.
     */
    private String name;

    /**
     * No args constructor.
     */
    public CarBrand() {
    }

    /**
     * Default constructor.
     *
     * @param id   id
     * @param name name
     */
    public CarBrand(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get car brand id.
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set car brand id.
     *
     * @param id new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get car brand name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set car brand name.
     *
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }
}