package ru.job4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Cache class.
 *
 * @author Denis
 * @since 28.03.2017
 */
class Cache {
    /**
     * Map with weak reference.
     */
    private Map<String, WeakReference<String>> weakMap;

    /**
     * Map with soft reference.
     */
    private Map<String, SoftReference<String>> softMap;

    /**
     * Default constructor.
     */
    Cache() {
        this.weakMap = new HashMap<>();
        this.softMap = new HashMap<>();
    }

    /**
     * Fill cache with file data.
     *
     * @param fileName file name
     * @return true if data is write in cache, false if data is already in cache
     */
    boolean fillCache(String fileName) {
        boolean result = false;
        if (this.softMap.get(fileName) == null || this.softMap.get(fileName).get() == null) {
            StringBuilder sb = new StringBuilder();
            try (FileReader fileReader = new FileReader(fileName);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String sCurrentLine;
                while ((sCurrentLine = bufferedReader.readLine()) != null) {
                    sb.append(String.format("%s%s", sCurrentLine, System.lineSeparator()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.weakMap.put(fileName, new WeakReference<>(sb.toString()));
            this.softMap.put(fileName, new SoftReference<>(sb.toString()));
            result = true;
        }
        return result;
    }

    /**
     * Get weak map data.
     *
     * @param fileName file name
     * @return value
     */
    String getWeakMap(String fileName) {
        return this.weakMap.get(fileName).get();
    }

    /**
     * Get soft map data.
     *
     * @param fileName file name
     * @return value
     */
    String getSoftMap(String fileName) {
        return this.softMap.get(fileName).get();
    }
}