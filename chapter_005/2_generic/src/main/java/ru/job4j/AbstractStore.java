package ru.job4j;

/**
 * AbstractStore class.
 *
 * @param <T> type
 * @author Denis
 * @since 26.02.2017
 */
public abstract class  AbstractStore<T extends Base> implements Store<T> {
    /**
     * Data.
     */
    private SimpleArray<T> data;

    /**
     * Data size.
     */
    private int size;

    /**
     * Main constructor.
     *
     * @param size data size
     */
    public AbstractStore(int size) {
        this.data = new SimpleArray<T>(size);
        this.size = size;
    }

    /**
     * Add element to collection.
     *
     * @param value new element
     * @return true if added successfully
     */
    @Override
    public boolean add(T value) {
        return data.add(value);
    }

    /**
     * Remove element from collection.
     *
     * @param id element id
     * @return true if remove successfully
     */
    @Override
    public boolean remove(String id) {
        boolean result = false;
        for (int i = 0; i < this.size; i++) {
            T value = data.get(i);
            if (value != null && id.equals(value.getId())) {
                result = data.delete(value);
                break;
            }
        }
        return result;
    }

    /**
     * Update element in collection.
     *
     * @param newValue new element
     * @param id       old element id
     * @return old element
     */
    @Override
    public T update(T newValue, String id) {
        T oldValue = null;
        for (int i = 0; i < this.size; i++) {
            T value = data.get(i);
            if (value != null && id.equals(value.getId())) {
                oldValue = value;
                data.update(newValue, i);
                break;
            }
        }
        return oldValue;
    }
}
