package ru.job4j;

/**
 * Base class.
 *
 * @author Denis
 * @since 26.02.2017
 */
public abstract class Base {
    /**
     * Name.
     */
    private String name;

    /**
     * Id.
     */
    private String id;

    /**
     * Main constructor.
     *
     * @param name name
     * @param id   id
     */
    public Base(String name, String id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Get id.
     *
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set id.
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * To string method.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return String.format("%s (id=%s)", this.name, this.id);
    }
}
