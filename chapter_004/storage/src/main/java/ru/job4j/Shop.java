package ru.job4j;

/**
 * Shop class.
 *
 * @author Denis
 * @since 12.02.2017
 */
public class Shop implements StorageI {
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
    public Shop(int storageSize) {
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
        double freshness = food.getFreshness(System.currentTimeMillis());
        if (this.position < this.foods.length) {
            if (freshness >= 0.25 & freshness < 1.0) {
                foods[position++] = food;
                result = true;
                setDiscount(food);
            }
        } else {
            System.out.println("Shop is full");
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
        boolean result = false;
        for (int i = 0; i < this.position; i++) {
            if (this.foods[i] != null && this.foods[i].equals(food)) {
                result = true;
                reallocateFood(i);
            }
        }
        return result;
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

    /**
     * Reallocate food.
     *
     * @param index index of reallocation.
     */
    private void reallocateFood(int index) {
        System.arraycopy(this.foods, index + 1, this.foods, index, this.position - index);
        this.position--;
    }

    /**
     * Setting discount.
     *
     * @param food food.
     */
    private void setDiscount(Food food) {
        food.setDiscount(0.5);
    }
}
