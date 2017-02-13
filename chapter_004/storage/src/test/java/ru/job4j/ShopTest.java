package ru.job4j;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * ShopTest class.
 *
 * @author Denis
 * @since 13.02.2017
 */
public class ShopTest {
    /**
     * Add one vegetable.
     */
    @Test
    public void whenAddOneVegetableThenOneVegetableInWarehouse() {
        StorageI storage = new Shop(10);
        Food vegetable0 = new Vegetable("vegetable0", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 2, 100, 0);
        ControlQuality controlQuality = new ControlQuality(storage);
        controlQuality.distributeOnStorage(vegetable0);
        assertThat(storage.getFoods()[0], is(vegetable0));
    }

    /**
     * Remove vegetable.
     */
    @Test
    public void whenRemoveVegetableThenReturnRemainedInWarehouse() {
        StorageI storage = new Shop(10);
        Food vegetable0 = new Vegetable("vegetable0", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 2, 100, 0);
        Food vegetable1 = new Vegetable("vegetable1", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 2, 100, 0);
        Food vegetable2 = new Vegetable("vegetable2", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 2, 100, 0);
        Food vegetable3 = new Vegetable("vegetable3", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 2, 100, 0);
        ControlQuality controlQuality = new ControlQuality(storage);
        controlQuality.distributeOnStorage(vegetable0, vegetable1, vegetable2, vegetable3);
        storage.removeFood(vegetable0);
        Food[] expected = new Food[10];
        expected[0] = vegetable1;
        expected[1] = vegetable2;
        expected[2] = vegetable3;
        assertThat(storage.getFoods(), is(expected));
    }

    /**
     * Full shop.
     */
    @Test
    public void whenAddVegetableInFullShopThenReturnMessage() {
        StorageI storage = new Shop(1);
        Food vegetable0 = new Vegetable("vegetable0", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 2, 100, 0);
        Food vegetable1 = new Vegetable("vegetable1", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 2, 100, 0);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String expected = Joiner.on(System.lineSeparator()).join("Shop is full", "");
        System.setOut(new PrintStream(outputStream));
        ControlQuality controlQuality = new ControlQuality(storage);
        controlQuality.distributeOnStorage(vegetable0, vegetable1);
        assertThat(outputStream.toString(), is(expected));
    }

    /**
     *
     */
    @Test
    public void whenVegetableIsOldThenSetDiscount() {
        StorageI storage = new Shop(10);
        Food vegetable0 = new Vegetable("vegetable0", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 200, 100, 0);
        ControlQuality controlQuality = new ControlQuality(storage);
        controlQuality.distributeOnStorage(vegetable0);
        assertThat(vegetable0.getDiscount(), is(0.5));
        assertThat(vegetable0.getPriceWithDiscount(), is(50.0));
    }
}