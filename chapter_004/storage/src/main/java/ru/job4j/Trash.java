package ru.job4j;

/**
 * Trash class.
 *
 * @author Denis
 * @since 12.02.2017
 */
public class Trash implements StorageI {
    /**
     * Food.
     */
    private Food[] foods;

    /**
     * Position.
     */
    private int position = 0;

    /**
     * Default constructor.
     *
     * @param storageSize storage size.
     */
    public Trash(int storageSize) {
        this.foods = new Food[storageSize];
    }

    /**
     * Add food.
     *
     * @param food food.
     * @return true if successfully add.
     */
    @Override
    public boolean addFood(Food food) {
        boolean result = false;
        if (this.position < this.foods.length) {
            if (food.getFreshness(System.currentTimeMillis()) >= 1.0) {
                this.foods[position++] = food;
                result = true;
            }
        } else {
            System.out.println("Trash is full");
        }
        return result;
    }

    /**
     * Move food.
     *
     * @param food food.
     * @return true if storage contain this food.
     */
    @Override
    public boolean removeFood(Food food) {
        return false;
    }

    /**
     * Get food.
     *
     * @return array of food.
     */
    @Override
    public Food[] getFoods() {
        return this.foods;
    }
}