package ru.job4j;

/**
 * Food class.
 *
 * @author Denis
 * @since 12.02.2017
 */
public abstract class Food {
    /**
     * Get freshness of food.
     *
     * @param currentDate current time.
     * @return freshness of food.
     */
    public abstract double getFreshness(double currentDate);

    /**
     * Get food name.
     *
     * @return name.
     */
    public abstract String getName();

    /**
     * Get date of creation.
     *
     * @return date of creation.
     */
    public abstract long getCreateDate();

    /**
     * Get date of expire.
     *
     * @return expires date.
     */
    public abstract long getExpireDate();

    /**
     * Get price without discount..
     *
     * @return price.
     */
    public abstract double getPrice();

    /**
     * Get price with discount.
     *
     * @return price with discount.
     */
    public abstract double getPriceWithDiscount();

    /**
     * Get discount percent.
     *
     * @return discount.
     */
    public abstract double getDiscount();

    /**
     * Set name of food.
     *
     * @param name name.
     */
    public abstract void setName(String name);

    /**
     * Set food price.
     *
     * @param price price.
     */
    public abstract void setPrice(double price);

    /**
     * Set create date.
     *
     * @param createDate createDate.
     */
    public abstract void setCreateDate(long createDate);

    /**
     * Set expire date.
     *
     * @param expireDate expireDate.
     */
    public abstract void setExpireDate(long expireDate);

    /**
     * Det discount percent.
     *
     * @param discount discount.
     */
    public abstract void setDiscount(double discount);
}
