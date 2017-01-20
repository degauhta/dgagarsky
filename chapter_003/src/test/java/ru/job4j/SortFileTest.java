package ru.job4j;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * SortFileTest class.
 *
 * @author Denis
 * @since 19.01.2017
 */
public class SortFileTest {
    /**
     * Test.
     */
    @Test
    public void testBubbleSort() {
        ClassLoader classLoader = getClass().getClassLoader();
        File source = new File(classLoader.getResource("in.txt").getFile());
        File distance = new File(source.getParent() + "\\out.txt");
        SortFile sortFile = new SortFile();
        new SortFile().sort(source, distance);
        sortFile.sort(source, distance);
        int[] lineSize = sortFile.getLineSize();
        for (int i = 0; i < lineSize.length - 1; i++) {
            assertThat(lineSize[i] < lineSize[(i + 1)], is(true));
        }
    }
}