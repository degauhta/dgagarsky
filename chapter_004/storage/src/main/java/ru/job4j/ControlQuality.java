package ru.job4j;

import java.util.Arrays;

/**
 * Control quality class.
 *
 * @author Denis
 * @since 12.02.2017
 */
public class ControlQuality {
    /**
     * Storages Array.
     */
    private Storage[] storageArray;

    /**
     * Default constructor.
     *
     * @param storageArray storageArray.
     */
    public ControlQuality(Storage... storageArray) {
        this.storageArray = storageArray;
    }

    /**
     * Distribute food on storage.
     *
     * @param foods foods.
     */
    public void distributeOnStorage(Food... foods) {
        for (Food food : foods) {
            for (Storage storage : this.storageArray) {
                if (food == null || storage == null) {
                    break;
                } else if (storage.addFood(food)) {
                    break;
                }
                storage.removeFood(food);
            }
        }
    }

    /**
     * Resort food in storage.
     */
    public void resort() {
        if (storageArray.length == 0) {
            return;
        }
        Food[] allFoods = new Food[storageArray[0].getFoods().length];
        System.arraycopy(storageArray[0].getFoods(), 0, allFoods, 0,
                storageArray[0].getFoods().length);
        int currentLength;
        removeFoodFromStorage(storageArray[0]);
        for (int i = 1; i < storageArray.length; i++) {
            currentLength = allFoods.length;
            allFoods = Arrays.copyOf(allFoods, currentLength + storageArray[i].getFoods().length);
            System.arraycopy(storageArray[i].getFoods(), 0, allFoods, currentLength,
                    storageArray[i].getFoods().length);
            removeFoodFromStorage(storageArray[i]);
        }
        distributeOnStorage(allFoods);
    }

    /**
     * Remove food.
     *
     * @param storage storage.
     */
    private void removeFoodFromStorage(Storage storage) {
        Food[] foods = storage.getFoods();
        for (int i = foods.length - 1; i >= 0; i--) {
            storage.removeFood(foods[i]);
        }
    }
}
