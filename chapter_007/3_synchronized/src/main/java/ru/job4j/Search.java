package ru.job4j;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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
    private BlockingQueue<File> txtFiles;

    /**
     * Stop when found phrase flag.
     */
    private boolean stopWhenFound;

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
        this.txtFiles = new LinkedBlockingQueue<>();
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
     * Return queue of files contains searching phrase.
     *
     * @return queue
     */
    BlockingQueue<File> getTxtFiles() {
        return this.txtFiles;
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
                txtFiles.add(fileEntry);
            }
        }
    }

    /**
     * Search phrase in files.
     */
    private synchronized void searchInFiles() {
        for (File file : this.txtFiles) {
            if (findPhraseInFile(file) & this.stopWhenFound) {
                this.txtFiles.clear();
                this.txtFiles.add(file);
                break;
            }
        }
    }

    /**
     * Searching phrase in particular file.
     *
     * @param file file
     * @return true if phrase is found
     */
    private boolean findPhraseInFile(File file) {
        boolean result = false;
        String line;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            do {
                line = in.readLine();
                if (line == null) {
                    break;
                }
                result = line.contains(this.phrase);
            } while (!result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!result) {
            this.txtFiles.remove(file);
        }
        return result;
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