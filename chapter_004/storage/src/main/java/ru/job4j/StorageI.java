package ru.job4j;

/**
 * StorageI class.
 *
 * @author Denis
 * @since 13.02.2017
 */
public interface StorageI {
    /**
     * Add food.
     *
     * @param food food.
     * @return true if successfully add.
     */
    boolean addFood(Food food);

    /**
     * Move food.
     *
     * @param food food.
     * @return true if storage contain this food.
     */
    boolean removeFood(Food food);

    /**
     * Get food.
     *
     * @return array of food.
     */
    Food[] getFoods();
}
