package ru.dega.models;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Advert class.
 *
 * @author Denis
 * @since 26.12.2017
 */
@Entity
@Table(name = "ADVERTISEMENTS")
public class Advert {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADVERTISEMENT_ID")
    private int id;

    /**
     * Description.
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * Car brand.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAR_BRAND_ID")
    private CarBrand carBrand;

    /**
     * Instantiates a new Advert.
     */
    public Advert() {
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets car brand.
     *
     * @return the car brand
     */
    public CarBrand getCarBrand() {
        return carBrand;
    }

    /**
     * Sets car brand.
     *
     * @param carBrand the car brand
     */
    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }
}