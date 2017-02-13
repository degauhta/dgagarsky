package ru.job4j;

/**
 * Warehouse class.
 *
 * @author Denis
 * @since 12.02.2017
 */
public class Warehouse implements StorageI {
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
    public Warehouse(int storageSize) {
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
            if (food.getFreshness(System.currentTimeMillis()) < 0.25) {
                foods[position++] = food;
                result = true;
            }
        } else {
            System.out.println("Warehouse is full");
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
}
