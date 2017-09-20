package ru.dega.models;

/**
 * CarBody class.
 *
 * @author Denis
 * @since 17.09.2017
 */
public class CarBody extends CarCategoryAbstract {
    /**
     * Car body id.
     */
    private int id;

    /**
     * Car body name.
     */
    private String name;

    /**
     * No args constructor.
     */
    public CarBody() {
    }

    /**
     * Instantiates a new Car body.
     *
     * @param id   the id
     * @param name the name
     */
    public CarBody(int id, String name) {
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