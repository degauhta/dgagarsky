package ru.job4j;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * TrashTest class.
 *
 * @author Denis
 * @since 13.02.2017
 */
public class TrashTest {
    /**
     * Add one meat.
     */
    @Test
    public void whenAddOneMeatThenOneMeatInTrash() {
        StorageI storage = new Trash(10);
        Food meat0 = new Meat("meat0", System.currentTimeMillis() - 1000,
                System.currentTimeMillis(), 100, 0);
        ControlQuality controlQuality = new ControlQuality(storage);
        controlQuality.distributeOnStorage(meat0);
        assertThat(storage.getFoods()[0], is(meat0));
    }

    /**
     * Remove meat.
     */
    @Test
    public void whenRemoveMeatThenReturnFalse() {
        StorageI storage = new Trash(10);
        Food meat0 = new Meat("meat0", System.currentTimeMillis() - 1000,
                System.currentTimeMillis(), 100, 0);
        ControlQuality controlQuality = new ControlQuality(storage);
        controlQuality.distributeOnStorage(meat0);
        assertThat(storage.removeFood(meat0), is(false));
    }

    /**
     * Full trash.
     */
    @Test
    public void whenAddMeatInFullTrashThenReturnMessage() {
        StorageI storage = new Trash(1);
        Food meat0 = new Meat("meat0", System.currentTimeMillis() - 1000,
                System.currentTimeMillis(), 100, 0);
        Food meat1 = new Meat("meat1", System.currentTimeMillis() - 1000,
                System.currentTimeMillis(), 100, 0);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String expected = Joiner.on(System.lineSeparator()).join("Trash is full", "");
        System.setOut(new PrintStream(outputStream));
        ControlQuality controlQuality = new ControlQuality(storage);
        controlQuality.distributeOnStorage(meat0, meat1);
        assertThat(outputStream.toString(), is(expected));
        assertThat(storage.getFoods()[0], is(meat0));
    }
}