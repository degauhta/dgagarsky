package ru.job4j;

import java.util.ArrayList;
import java.util.List;

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
        List<Food> list = new ArrayList<>();
        for (int i = 0; i < storageArray.length; i++) {
            for (Food food: storageArray[i].getFoods()) {
                if (food != null) {
                    list.add(food);
                }
            }
            removeFoodFromStorage(storageArray[i]);
        }
        Food[] foods = new Food[list.size()];
        foods = list.toArray(foods);
        distributeOnStorage(foods);
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
