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
    private StorageI[] storageArray;

    /**
     * Default constructor.
     *
     * @param storageArray storageArray.
     */
    public ControlQuality(StorageI ... storageArray) {
        this.storageArray = storageArray;
    }

    /**
     * Distribute food on storage.
     *
     * @param foods foods.
     */
    public void distributeOnStorage(Food ... foods) {
        for (Food food : foods) {
            for (StorageI storage: this.storageArray) {
                if (storage.addFood(food)) {
                    break;
                }
            }
        }
    }
}
