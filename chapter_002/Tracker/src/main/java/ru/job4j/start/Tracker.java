package ru.job4j.start;

import ru.job4j.models.Bug;
import ru.job4j.models.Item;
import ru.job4j.models.Task;

/**
 * Created by Denis on 18.12.2016.
 */
public class Tracker {
    /**
     * Items length.
     */
    private final int itemLength = 10;
    /**
     * Array-storage for requests.
     */
    private Item[] items = new Item[itemLength];
    /**
     * Index of request.
     */
    private int position = 0;

    /**
     * Add request to storage.
     *
     * @param item request.
     * @return ref to request.
     */
    public Item add(Item item) {
        item.setId(java.util.UUID.randomUUID().toString());
        this.items[position++] = item;
        return item;
    }

    /**
     * Find request. Searching in fields name and description.
     *
     * @param sub substring to find.
     * @return finding request.
     */
    public Item[] filterRequest(String sub) {
        Item[] result = null;
        int lenght = 1;
        for (Item item : items) {
            if (item != null && (item.getName().toLowerCase().contains(sub.toLowerCase())
                    || item.getDescription().toLowerCase().contains(sub.toLowerCase()))) {
                if (result == null) {
                    result = new Item[lenght];
                    result[lenght - 1] = item;
                } else {
                    Item[] resultNew = new Item[lenght];
                    System.arraycopy(result, 0, resultNew, 0, lenght - 1);
                    result = resultNew;
                    result[lenght - 1] = item;
                }
                lenght++;
            }
        }
        return result;
    }

    /**
     * Print all requests.
     *
     * @return all requests.
     */
    public Item[] getAll() {
        Item[] result = new Item[this.position];
        for (int i = 0; i != this.position; i++) {
            result[i] = this.items[i];
        }
        return result;
    }

    /**
     * Editing request.
     *
     * @param item        request to edit.
     * @param name        new name or null.
     * @param description new description or null.
     * @return edited request.
     */
    public Item editRequest(Item item, String name, String description) {
        for (int i = 0; i != position; i++) {
            if (this.items[i] == item) {
                if (name != null) {
                    item.setName(name);
                }
                if (description != null) {
                    item.setDescription(description);
                }
            }
        }
        return item;
    }

    /**
     * @param removeItem request that need to delete.
     * @return true if remove successful.
     */
    public boolean removeRequest(Item removeItem) {
        boolean result = false;
        for (int i = 0; i != position; i++) {
            if (this.items[i] == removeItem) {
                Item[] newItems = new Item[position - 1];
                System.arraycopy(this.items, 0, newItems, 0, i);
                for (int j = i; j != position - 1;) {
                    newItems[j] = this.items[++j];
                }
                this.items = newItems;
                result = true;
                position--;
                break;
            }
        }
        return result;
    }

    /**
     * Main method.
     * @param args - args.
     */
    public static void main(final String[] args) {
        Tracker tracker = new Tracker();
        tracker.add(new Item("item", "item desc", 1L));
        tracker.add(new Task("task", "task desc", 1L));
        tracker.add(new Bug("bug", "bug desc", 1L));
        for (Item item : tracker.getAll()) {
            System.out.println(item.getName());
        }
    }
}
