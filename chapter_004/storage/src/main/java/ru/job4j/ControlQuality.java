package ru.job4j;

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
}
