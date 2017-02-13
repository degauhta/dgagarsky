package ru.job4j;

/**
 * Fruit class.
 *
 * @author Denis
 * @since 12.02.2017
 */
public class Fruit extends Food {
    /**
     * Default constructor.
     *
     * @param name       name.
     * @param createDate create date.
     * @param expireDate expire date.
     * @param price      price.
     * @param discount   discount.
     */
    public Fruit(String name, long createDate, long expireDate, double price, double discount) {
        super(name, createDate, expireDate, price, discount);
    }
}
