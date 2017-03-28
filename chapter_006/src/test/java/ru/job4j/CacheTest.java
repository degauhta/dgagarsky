package ru.job4j;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
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
     * Init.
     */
    @Before
    public void init() {
        ClassLoader classLoader = getClass().getClassLoader();
        File source = new File(classLoader.getResource("Names.txt").getFile());
        this.namesPath = source.getPath();
        this.cache = new Cache();
    }

    /**
     * Test read file once.
     */
    @Test
    public void whenReadFileOnceThenReturnTrue() {
        assertThat(this.cache.fillCache(this.namesPath), is(true));
    }

    /**
     * Test read file twice.
     */
    @Test
    public void whenReadFileTwiceThenReturnFalse() {
        this.cache.fillCache(this.namesPath);
        assertThat(this.cache.fillCache(this.namesPath), is(false));
    }

    /**
     * Test weal reference after GC work.
     */
    @Test
    public void whenGCInvokeThenWeakReferenceIsNullAndSoftHaveData() {
        cache.fillCache(namesPath);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(String.format("%s%s%s", "Name", i, System.lineSeparator()));
        }
        System.gc();
        assertThat(this.cache.getSoftMap(this.namesPath), is(sb.toString()));
        assertThat(this.cache.getWeakMap(this.namesPath), is(nullValue()));
    }
}