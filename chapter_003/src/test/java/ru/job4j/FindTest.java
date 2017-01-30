package ru.job4j;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * FindTest class.
 *
 * @author Denis
 * @since 28.01.2017
 */
public class FindTest {
    /**
     * Test log file.
     *
     * @throws IOException .
     */
    @Test
    public void whenRightKeysThenLogFile() throws IOException {
        String disk = new File(".").getCanonicalPath().substring(0, 3);
        File parentDir;
        do {
            parentDir = new File(disk + "\\" + System.currentTimeMillis());
        } while (parentDir.exists());
        File file = new File(parentDir + "\\1.txt");
        file.getParentFile().mkdirs();
        file.createNewFile();

        file = new File(parentDir + "\\321\\2.txt");
        file.getParentFile().mkdirs();
        file.createNewFile();

        Find find = new Find();
        find.runSearching("-d \"" + parentDir + "\" -n \"*.txt\" -m -o \"log.txt\"");

        String actual = find.getClass().getClassLoader().getResource(".").getPath() + "\\log.txt";
        actual = FileUtils.readFileToString(new File(actual), "utf-8");
        String expected = parentDir + "\\1.txt" + System.getProperty("line.separator")
                + parentDir + "\\321\\2.txt";
        assertThat(expected, is(actual));
        FileUtils.forceDelete(parentDir);
    }

    /**
     * Test directory open quotes.
     */
    @Test
    public void whenNoOpenQuotesInDirThenMessageNotValidKeys() {
        Find find = new Find();
        assertThat(find.runSearching("-d c:/1\" -n \"*.txt\" -m -o \"log.txt\""), is("Not valid keys"));
    }

    /**
     * Test directory closed quotes.
     */
    @Test
    public void whenNoClosedQuotesInDirThenMessageNotValidKeys() {
        Find find = new Find();
        assertThat(find.runSearching("-d \"c:/1 -n \"*.txt\" -m -o \"log.txt\""), is("Not valid keys"));
    }

    /**
     * Test directory wrong path.
     */
    @Test
    public void whenWrongPathInDirThenMessageNotValidKeys() {
        Find find = new Find();
        assertThat(find.runSearching("-d \"111\" -n \"*.txt\" -m -o \"log.txt\""), is("Not valid keys"));
    }

    /**
     * Test name open quotes.
     */
    @Test
    public void whenNoOpenQuotesInNameThenMessageNotValidKeys() {
        Find find = new Find();
        assertThat(find.runSearching("-d \"c:/1\" -n *.txt\" -m -o \"log.txt\""), is("Not valid keys"));
    }

    /**
     * Test name closed quotes.
     */
    @Test
    public void whenNoClosedQuotesInNameThenMessageNotValidKeys() {
        Find find = new Find();
        assertThat(find.runSearching("-d \"c:/1\" -n \"*.txt -m -o \"log.txt\""), is("Not valid keys"));
    }

    /**
     * Test log open quotes.
     */
    @Test
    public void whenNoOpenQuotesInLogThenMessageNotValidKeys() {
        Find find = new Find();
        assertThat(find.runSearching("-d \"c:/1\" -n \"*.txt\" -m -o log.txt\""), is("Not valid keys"));
    }

    /**
     * Test log closed quotes.
     */
    @Test
    public void whenNoClosedQuotesInLogThenMessageNotValidKeys() {
        Find find = new Find();
        assertThat(find.runSearching("-d \"c:/1\" -n \"*.txt\" -m -o \"log.txt"), is("Not valid keys"));
    }
}