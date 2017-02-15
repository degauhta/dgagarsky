package ru.job4j;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * WarehouseTest class.
 *
 * @author Denis
 * @since 13.02.2017
 */
public class WarehouseTest {
    /**
     * Add one fruit.
     */
    @Test
    public void whenAddOneFruitThenOneFruitInWarehouse() {
        Storage storage = new Warehouse(10);
        Food apple0 = new Fruit("apple0", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 10, 100, 0);
        ControlQuality controlQuality = new ControlQuality(storage);
        controlQuality.distributeOnStorage(apple0);
        assertThat(storage.getFoods()[0], is(apple0));
    }

    /**
     * Remove fruit.
     */
    @Test
    public void whenRemoveFruitThenReturnRemainedInWarehouse() {
        Storage storage = new Warehouse(10);
        Food apple0 = new Fruit("apple0", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 10, 100, 0);
        Food apple1 = new Fruit("apple1", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 10, 100, 0);
        Food apple2 = new Fruit("apple2", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 10, 100, 0);
        Food apple3 = new Fruit("apple3", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 10, 100, 0);
        ControlQuality controlQuality = new ControlQuality(storage);
        controlQuality.distributeOnStorage(apple0, apple1, apple2, apple3);
        storage.removeFood(apple0);
        Food[] expected = new Food[10];
        expected[0] = apple1;
        expected[1] = apple2;
        expected[2] = apple3;
        assertThat(storage.getFoods(), is(expected));
    }

    /**
     * Full warehouse.
     */
    @Test
    public void whenAddFruitInFullWarehouseThenReturnMessage() {
        Storage storage = new Warehouse(1);
        Food apple0 = new Fruit("apple0", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 10, 100, 0);
        Food apple1 = new Fruit("apple1", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 10, 100, 0);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String expected = Joiner.on(System.lineSeparator()).join("Warehouse is full", "");
        System.setOut(new PrintStream(outputStream));
        ControlQuality controlQuality = new ControlQuality(storage);
        controlQuality.distributeOnStorage(apple0, apple1);
        assertThat(outputStream.toString(), is(expected));
        assertThat(storage.getFoods()[0], is(apple0));
    }

    /**
     * Food get & set.
     */
    @Test
    public void testGetAndSet() {
        Food apple = new Fruit("apple0", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 10, 100, 0);
        Food meat = new Meat("meat", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 10, 100, 0);
        Food vegetable = new Vegetable("vegetable", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 10, 100, 0);

        apple.setName("fruit");
        assertThat(apple.getName(), is("fruit"));
        apple.setPrice(330);
        assertThat(apple.getPrice(), is(330.0));
        apple.setCreateDate(123);
        assertThat(apple.getCreateDate(), is(123L));
        apple.setExpireDate(567);
        assertThat(apple.getExpireDate(), is(567L));

        meat.setName("meat");
        assertThat(meat.getName(), is("meat"));
        meat.setPrice(330);
        assertThat(meat.getPrice(), is(330.0));
        meat.setCreateDate(123);
        assertThat(meat.getCreateDate(), is(123L));
        meat.setExpireDate(567);
        assertThat(meat.getExpireDate(), is(567L));

        vegetable.setName("vegetable");
        assertThat(vegetable.getName(), is("vegetable"));
        vegetable.setPrice(330);
        assertThat(vegetable.getPrice(), is(330.0));
        vegetable.setCreateDate(123);
        assertThat(vegetable.getCreateDate(), is(123L));
        vegetable.setExpireDate(567);
        assertThat(vegetable.getExpireDate(), is(567L));
    }

}