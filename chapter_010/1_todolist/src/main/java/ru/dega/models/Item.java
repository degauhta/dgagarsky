package ru.dega.models;

import java.sql.Timestamp;

/**
 * Item class.
 *
 * @author Denis
 * @since 11.09.2017
 */
public class Item {
    /**
     * Item id.
     */
    private int id;

    /**
     * Item description.
     */
    private String description;

    /**
     * Item created date.
     */
    private Timestamp created;

    /**
     * Item done status.
     */
    private boolean done;

    /**
     * Default constructor.
     *
     * @param id          item id
     * @param description item description
     * @param created     item created date
     * @param done        item done status
     */
    public Item(int id, String description, Timestamp created, boolean done) {
        this.id = id;
        this.description = description;
        this.created = created;
        this.done = done;
    }

    /**
     * No args constructor.
     */
    public Item() {
    }

    /**
     * Get id of item.
     *
     * @return item id
     */
    public int getId() {
        return id;
    }

    /**
     * Set item id.
     *
     * @param id new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get item description.
     *
     * @return item description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set item description.
     *
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get item created date.
     *
     * @return created date
     */
    public Timestamp getCreated() {
        return created;
    }

    /**
     * Set item created date.
     *
     * @param created new created date
     */
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * Get item done status.
     *
     * @return item done status
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Set item done status.
     *
     * @param done new done status
     */
    public void setDone(boolean done) {
        this.done = done;
    }
}