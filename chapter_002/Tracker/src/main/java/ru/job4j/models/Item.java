package ru.job4j.models;

/**
 * Base class for all requests.
 */
public class Item {
    /**
     * Request name.
     */
    private String name;
    /**
     * Request description.
     */
    private String description;
    /**
     * Request create date.
     */
    private long create;
    /**
     * Request comments.
     */
    private String[] comments;
    /**
     * Unique id.
     */
    private String id;

    /**
     * Class constructor.
     *
     * @param name        name bug.
     * @param description short description.
     * @param create      data of creation.
     */
    public Item(String name, String description, long create) {
        this.name = name;
        this.description = description;
        this.create = create;
    }

    /**
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return create date.
     */
    public long getCreate() {
        return create;
    }

    /**
     * @param create set.
     */
    public void setCreate(long create) {
        this.create = create;
    }

    /**
     * @return id.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id set id.
     */
    public void setId(String id) {
        this.id = id;
    }

}
