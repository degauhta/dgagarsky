package ru.dega.models;

import org.springframework.data.annotation.Id;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * CarBrand class.
 *
 * @author Denis
 * @since 26.12.2017
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

    @OneToMany(mappedBy="carBrand", cascade = CascadeType.ALL)
    private List<Advert> linkedAdverts;

    /**
     * Instantiates a new User.
     */
    public CarBrand() {
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

    public List<Advert> getLinkedAdverts() {
        return linkedAdverts;
    }

    public void setLinkedAdverts(List<Advert> linkedAdverts) {
        this.linkedAdverts = linkedAdverts;
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