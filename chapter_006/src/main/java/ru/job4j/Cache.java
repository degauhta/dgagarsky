package ru.job4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
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
     * Map with soft reference.
     */
    private Map<String, SoftReference<String>> softMap;

    /**
     * Cache flag.
     */
    private boolean cacheFilled;

    /**
     * Default constructor.
     */
    Cache() {
        this.softMap = new HashMap<>();
        this.cacheFilled = false;
    }

    /**
     * Read file if it not in cache.
     * If it already in cache then return data from cache.
     *
     * @param fileName file name
     * @return file data
     */
    String readFile(String fileName) {
        this.cacheFilled = false;
        StringBuilder sb = new StringBuilder();
        if (this.softMap.get(fileName) == null || this.softMap.get(fileName).get() == null) {
            try (FileReader fileReader = new FileReader(fileName);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String sCurrentLine;
                while ((sCurrentLine = bufferedReader.readLine()) != null) {
                    sb.append(String.format("%s%s", sCurrentLine, System.lineSeparator()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.softMap.put(fileName, new SoftReference<>(sb.toString()));
            this.cacheFilled = true;
        }
        return sb.length() == 0 ? this.softMap.get(fileName).get() : sb.toString();
    }

    /**
     * Get cache filled flag.
     *
     * @return true if last file was read from cache
     */
    boolean isCacheFilled() {
        return this.cacheFilled;
    }
}