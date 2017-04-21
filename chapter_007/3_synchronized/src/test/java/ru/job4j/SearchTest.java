package ru.job4j;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * SearchTest class.
 *
 * @author Denis
 * @since 19.04.2017
 */
public class SearchTest {
    /**
     * Parent dir path.
     */
    private File parentDir;

    /**
     * Initialization.
     *
     * @throws IOException error
     */
    @Before
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void init() throws IOException {
        String disk = new File(".").getCanonicalPath().substring(0, 3);
        parentDir = new File(disk + "\\" + System.currentTimeMillis());
        File file = new File(parentDir + "\\1.txt");
        file.getParentFile().mkdirs();
        file.createNewFile();
        writeText(file, "qwerty - test");
        file = new File(parentDir + "\\2.txt");
        file.createNewFile();
        writeText(file, "qwerty");
        file = new File(parentDir + "\\321\\3.txt");
        file.getParentFile().mkdirs();
        file.createNewFile();
        writeText(file, "1232 test");
    }

    /**
     * Write text in file.
     *
     * @param file file
     * @param text text
     */
    private void writeText(File file, String text) {
        try (PrintWriter out = new PrintWriter(file)) {
            out.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete temp dir.
     *
     * @throws IOException error
     */
    @After
    public void del() throws IOException {
        FileUtils.forceDelete(parentDir);
    }

    /**
     * Test search without stop flag.
     */
    @Test
    public void whenSearchWithoutFlagThenFind2Files() {
        Search search = new Search("test", parentDir, false);
        search.start();
        assertThat(search.getFindInFiles().size(), is(2));
    }

    /**
     * Test search with stop flag.
     */
    @Test
    public void whenSearchWithFlagThenFind1Files() {
        Search search = new Search("test", parentDir, true);
        search.start();
        assertThat(search.getFindInFiles().size(), is(1));
    }
}