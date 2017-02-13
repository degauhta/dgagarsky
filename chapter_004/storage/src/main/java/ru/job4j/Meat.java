package ru.job4j;

/**
 * Meat class.
 *
 * @author Denis
 * @since 12.02.2017
 */
public class Meat extends Food {
    /**
     * Default constructor.
     *
     * @param name       name.
     * @param createDate create date.
     * @param expireDate expire date.
     * @param price      price.
     * @param discount   discount.
     */
    public Meat(String name, long createDate, long expireDate, double price, double discount) {
        super(name, createDate, expireDate, price, discount);
    }
}
