package ru.dega.models;

/**
 * CarModel class.
 *
 * @author Denis
 * @since 17.09.2017
 */
public class CarModel extends CarCategoryAbstract {
    /**
     * Car model id.
     */
    private int id;

    /**
     * Car model name.
     */
    private String name;

    /**
     * No args constructor.
     */
    public CarModel() {
    }

    /**
     * Instantiates a new Car model.
     *
     * @param id   the id
     * @param name the name
     */
    public CarModel(int id, String name) {
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