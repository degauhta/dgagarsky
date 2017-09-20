package ru.dega.models;

/**
 * Advertisement class.
 *
 * @author Denis
 * @since 16.09.2017
 */
public class Advertisement {
    /**
     * Advert Id.
     */
    private int id;

    /**
     * Advert description.
     */
    private String description;

    /**
     * Advert sold status.
     */
    private boolean sold;

    /**
     * Advert photo.
     */
    private byte[] photo;

    /**
     * Photo in Base64String format.
     */
    private String photoStr;

    /**
     * Advert author.
     */
    private User author;

    /**
     * Advert car brand.
     */
    private CarBrand carBrand;

    /**
     * Advert car model.
     */
    private CarModel carModel;

    /**
     * Advert car body.
     */
    private CarBody carBody;

    /**
     * Advert car transmission.
     */
    private CarTransmission carTransmission;

    /**
     * Advert car engine.
     */
    private CarEngine carEngine;

    /**
     * Advert car drive type.
     */
    private CarDriveType carDriveType;

    /**
     * No args constructor.
     */
    public Advertisement() {
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
     * Is sold boolean.
     *
     * @return the boolean
     */
    public boolean isSold() {
        return sold;
    }

    /**
     * Sets sold.
     *
     * @param sold the sold
     */
    public void setSold(boolean sold) {
        this.sold = sold;
    }

    /**
     * Get photo byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * Sets photo.
     *
     * @param photo the photo
     */
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    /**
     * Sets photo str.
     *
     * @param photoStr the photo str
     */
    public void setPhotoStr(String photoStr) {
        this.photoStr = photoStr;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(User author) {
        this.author = author;
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

    /**
     * Gets car model.
     *
     * @return the car model
     */
    public CarModel getCarModel() {
        return carModel;
    }

    /**
     * Sets car model.
     *
     * @param carModel the car model
     */
    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    /**
     * Gets car body.
     *
     * @return the car body
     */
    public CarBody getCarBody() {
        return carBody;
    }

    /**
     * Sets car body.
     *
     * @param carBody the car body
     */
    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    /**
     * Gets car transmission.
     *
     * @return the car transmission
     */
    public CarTransmission getCarTransmission() {
        return carTransmission;
    }

    /**
     * Sets car transmission.
     *
     * @param carTransmission the car transmission
     */
    public void setCarTransmission(CarTransmission carTransmission) {
        this.carTransmission = carTransmission;
    }

    /**
     * Gets car engine.
     *
     * @return the car engine
     */
    public CarEngine getCarEngine() {
        return carEngine;
    }

    /**
     * Sets car engine.
     *
     * @param carEngine the car engine
     */
    public void setCarEngine(CarEngine carEngine) {
        this.carEngine = carEngine;
    }

    /**
     * Gets car drive type.
     *
     * @return the car drive type
     */
    public CarDriveType getCarDriveType() {
        return carDriveType;
    }

    /**
     * Sets car drive type.
     *
     * @param carDriveType the car drive type
     */
    public void setCarDriveType(CarDriveType carDriveType) {
        this.carDriveType = carDriveType;
    }
}