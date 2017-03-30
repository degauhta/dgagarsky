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
     * Get data from cache.
     *
     * @param fileName file name
     * @return file data
     */
    String get(String fileName) {
        this.cacheFilled = false;
        SoftReference<String> content = this.softMap.get(fileName);
        if (content == null) {
            content = loadFile(fileName);
            this.softMap.put(fileName, content);
            this.cacheFilled = true;
        }
        return content.get();
    }

    /**
     * Read file.
     *
     * @param fileName file name
     * @return string representation of file data
     */
    private SoftReference<String> loadFile(String fileName) {
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
        return new SoftReference<>(sb.toString());
    }

    /**
     * Get cache filled flag.
     *
     * @return true if last file was loadFile from cache
     */
    boolean isCacheFilled() {
        return this.cacheFilled;
    }
}