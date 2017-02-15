package ru.job4j;

/**
 * Vegetable class.
 *
 * @author Denis
 * @since 12.02.2017
 */
public class Vegetable extends Food {
    /**
     * Name of the food.
     */
    private String name;

    /**
     * Date of create.
     */
    private long createDate;

    /**
     * Expires date.
     */
    private long expireDate;

    /**
     * Price.
     */
    private double price;

    /**
     * Discount percent.
     */
    private double discount;

    /**
     * Default constructor.
     *
     * @param name       name.
     * @param createDate create date.
     * @param expireDate expire date.
     * @param price      price.
     * @param discount   discount.
     */
    public Vegetable(final String name, final long createDate, final long expireDate, double price, double discount) {
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.price = price;
        this.discount = discount;
    }

    /**
     * Get freshness of food.
     *
     * @param currentDate current time.
     * @return freshness of food.
     */
    public double getFreshness(double currentDate) {
        return (currentDate - this.createDate) / (this.expireDate - this.createDate);
    }

    /**
     * Get food name.
     *
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get date of creation.
     *
     * @return date of creation.
     */
    public long getCreateDate() {
        return this.createDate;
    }

    /**
     * Get date of expire.
     *
     * @return expires date.
     */
    public long getExpireDate() {
        return this.expireDate;
    }

    /**
     * Get price without discount..
     *
     * @return price.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Get price with discount.
     *
     * @return price with discount.
     */
    public double getPriceWithDiscount() {
        return this.price - this.price * this.discount;
    }

    /**
     * Get discount percent.
     *
     * @return discount.
     */
    public double getDiscount() {
        return this.discount;
    }

    /**
     * Set name of food.
     *
     * @param name name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set food price.
     *
     * @param price price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Set create date.
     *
     * @param createDate createDate.
     */
    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    /**
     * Set expire date.
     *
     * @param expireDate expireDate.
     */
    public void setExpireDate(long expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Det discount percent.
     *
     * @param discount discount.
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
