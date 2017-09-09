package ru.dega.models;

/**
 * MusicType class.
 *
 * @author Denis
 * @since 31.08.2017
 */
public class MusicType {
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
    public MusicType(int id, String name) {
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