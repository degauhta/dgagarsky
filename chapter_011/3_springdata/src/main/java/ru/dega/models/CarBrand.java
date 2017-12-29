package ru.dega.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CarBrand class.
 *
 * @author Denis
 * @since 28.12.2017
 */
@Entity
@Table(name = "CAR_BRANDS")
public class CarBrand {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CAR_BRAND_ID")
    private int id;

    /**
     * Name.
     */
    @Column(name = "NAME")
    private String name;

    /**
     * Instantiates a new User.
     */
    public CarBrand() {
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

    /**
     * To string.
     *
     * @return name
     */
    @Override
    public String toString() {
        return this.name;
    }
}