package ru.dega.models;

/**
 * Role class.
 *
 * @author Denis
 * @since 29.08.2017
 */
public class Role {
    /**
     * ID.
     */
    private int id;

    /**
     * Name.
     */
    private String name;

    /**
     * Default constructor.
     *
     * @param id   id
     * @param name name
     */
    public Role(int id, String name) {
        this.id = id;
        this.name = name;
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
     * Get name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }
}