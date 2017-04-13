package ru.job4j;

/**
 * User class.
 *
 * @author Denis
 * @since 12.04.2017
 */
class User {
    /**
     * Name.
     */
    private String name;

    /**
     * Amount.
     */
    private int amount;

    /**
     * Default constructor.
     *
     * @param name   name
     * @param amount amount
     */
    User(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    /**
     * Get name.
     *
     * @return name
     */
    String getName() {
        return name;
    }

    /**
     * Set name.
     *
     * @param name new name
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Get amount.
     *
     * @return amount
     */
    int getAmount() {
        return amount;
    }

    /**
     * Set amount.
     *
     * @param amount new amount
     */
    void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + amount;
        return result;
    }
}

