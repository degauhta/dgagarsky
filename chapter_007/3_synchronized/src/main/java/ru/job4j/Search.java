package ru.job4j;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Search class.
 *
 * @author Denis
 * @since 19.04.2017
 */
class Search {
    /**
     * All txt file in search folder and subFolders.
     */
    private Map<File, Boolean> txtFiles;

    /**
     * Files contains search phrase.
     */
    private List<File> findInFiles;

    /**
     * Stop when found phrase flag.
     */
    private boolean stopWhenFound;

    /**
     * Found phrase flag.
     */
    private boolean found;

    /**
     * Search phrase.
     */
    private String phrase;

    /**
     * Searching start folder.
     */
    private File path;

    /**
     * Default constructor.
     *
     * @param phrase        search phrase
     * @param path          start path
     * @param stopWhenFound stop search when file found
     */
    Search(String phrase, File path, boolean stopWhenFound) {
        this.txtFiles = new HashMap<>();
        this.findInFiles = new ArrayList<>();
        this.stopWhenFound = stopWhenFound;
        this.phrase = phrase;
        this.path = path;
    }

    /**
     * Start multi thread search.
     */
    void start() {
        createMapOfTxtFiles(this.path);
        if (txtFiles.size() > 0) {
            Thread th1 = new Thread(new MyRun());
            Thread th2 = new Thread(new MyRun());
            th1.start();
            th2.start();
            try {
                th1.join();
                th2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Return list of files contains searching phrase.
     *
     * @return list variable
     */
    synchronized List<File> getFindInFiles() {
        return this.findInFiles;
    }

    /**
     * Create map all txt file in search folder and subFolders.
     *
     * @param folder folder to search
     */
    private void createMapOfTxtFiles(File folder) {
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                createMapOfTxtFiles(fileEntry);
            } else {
                txtFiles.put(fileEntry, false);
            }
        }
    }

    /**
     * Search phrase in files.
     */
    private synchronized void searchInFiles() {
        File file;
        do {
            file = findNotCheckedFile();
            if (file != null) {
                findPhraseInFile(file);
            }
        } while (!this.found & file != null);
    }

    /**
     * Find not checked file among all files.
     *
     * @return not checked file or null
     */
    private synchronized File findNotCheckedFile() {
        File result = null;
        for (Map.Entry<File, Boolean> entry : txtFiles.entrySet()) {
            if (!entry.getValue()) {
                result = entry.getKey();
                entry.setValue(true);
                break;
            }
        }
        return result;
    }

    /**
     * Searching phrase in particular file.
     *
     * @param file file
     */
    private synchronized void findPhraseInFile(File file) {
        boolean result = false;
        String line;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            do {
                line = in.readLine();
                if (line == null) {
                    break;
                }
                result = line.contains(this.phrase);
                if (result) {
                    this.findInFiles.add(file);
                }
            } while (!result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result & this.stopWhenFound) {
            this.found = true;
        }
    }

    /**
     * Runnable class.
     */
    private class MyRun implements Runnable {
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            searchInFiles();
        }
    }
}