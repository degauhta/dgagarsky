package ru.job4j;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * CacheTest class.
 *
 * @author Denis
 * @since 28.03.2017
 */
public class CacheTest {
    /**
     * Cache.
     */
    private Cache cache;

    /**
     * Names path.
     */
    private String namesPath;

    /**
     * Data from file Names.
     */
    private String sNames;

    /**
     * Init.
     */
    @Before
    public void init() {
        ClassLoader classLoader = getClass().getClassLoader();
        File source = new File(classLoader.getResource("Names.txt").getFile());
        this.namesPath = source.getPath();
        this.cache = new Cache();
        StringBuilder sbNames = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sbNames.append(String.format("%s%s%s", "Name", i, System.lineSeparator()));
        }
        this.sNames = sbNames.toString();
    }

    /**
     * Test read file once.
     */
    @Test
    public void whenReadFileOnceThenReturnTrue() {
        assertThat(this.cache.get(this.namesPath), is(this.sNames));
        assertThat(this.cache.isCacheFilled(), is(true));
    }

    /**
     * Test read file twice.
     */
    @Test
    public void whenReadFileTwiceThenReturnFalse() {
        this.cache.get(this.namesPath);
        assertThat(this.cache.get(this.namesPath), is(this.sNames));
        assertThat(this.cache.isCacheFilled(), is(false));
    }
}