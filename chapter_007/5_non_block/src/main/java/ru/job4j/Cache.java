package ru.job4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Cache class.
 *
 * @author Denis
 * @since 04.05.2017
 */
class Cache {
    /**
     * Map.
     */
    private ConcurrentMap<Integer, Model> map;

    /**
     * Default constructor.
     */
    Cache() {
        this.map = new ConcurrentHashMap<>();
    }

    /**
     * Get model by key.
     *
     * @param key key
     * @return model or null
     */
    Model get(int key) {
        return this.map.get(key);
    }

    /**
     * Add to cache.
     *
     * @param key      key
     * @param newValue model
     */
    void add(int key, Model newValue) {
        this.map.putIfAbsent(key, newValue);
    }

    /**
     * Delete entry.
     *
     * @param key key
     */
    void delete(int key) {
        this.map.computeIfPresent(key, (integer, model) -> null);
    }

    /**
     * Ge size.
     *
     * @return size
     */
    int size() {
        return this.map.size();
    }

    /**
     * Update data of model.
     *
     * @param key     key
     * @param newName new name
     */
    void update(int key, String newName) {
        this.map.computeIfPresent(key, (k, v) -> {
            int old = v.getVersion();
            /*System.out.println(String.format("thread=%s, startVersion=%s", Thread.currentThread().getName(), old));*/
            v.setName(newName);
            if (!v.compareAndSetVersion(old)) {
                throw new OptimisticException();
            }
            return v;
        });
    }
}