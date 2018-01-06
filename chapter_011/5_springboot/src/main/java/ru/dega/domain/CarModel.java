package ru.dega.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CarModel class.
 *
 * @author Denis
 * @since 29.12.2017
 */
@Entity
@Table(name = "CAR_MODELS")
public class CarModel {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CAR_MODEL_ID")
    private int id;

    /**
     * Name.
     */
    @Column(name = "NAME")
    private String name;

    /**
     * Instantiates a new Car model.
     */
    public CarModel() {
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