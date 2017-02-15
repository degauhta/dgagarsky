package ru.job4j;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * FoodReproducerTest class.
 *
 * @author Denis
 * @since 15.02.2017
 */
public class FoodReproducerTest {
    /**
     * Add reproduce food.
     */
    @Test
    public void whenAddOneReproducedFoodThenOneReproducedFoodInShop() {
        Storage storage = new Shop(10);
        Food fruit = new Fruit("fruit", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 2, 100, 0);
        Food reproduced = new FoodReproducer(fruit, true);
        ControlQuality controlQuality = new ControlQuality(storage);
        controlQuality.distributeOnStorage(reproduced);
        assertThat(storage.getFoods()[0], is(reproduced));
    }

    /**
     * Add reproduce food.
     *
     * @throws InterruptedException error.
     */
    @Test
    public void whenFreshnessOfReproducedFoodIs100ThenReduceFreshnessTo50Percent() throws InterruptedException {
        Storage shop = new Shop(10);
        Food freshFruit = new Fruit("fruit", System.currentTimeMillis() - 100,
                System.currentTimeMillis() + 50, 100, 0);
        Food notFreshFruit = new Fruit("fruit", System.currentTimeMillis(),
                System.currentTimeMillis(), 100, 0);
        Food reproduced = new FoodReproducer(freshFruit, true);
        ControlQuality controlQuality = new ControlQuality(shop);

        controlQuality.distributeOnStorage(freshFruit);
        assertThat(shop.getFoods()[0], is(freshFruit));

        controlQuality.distributeOnStorage(notFreshFruit);
        assertThat(shop.getFoods()[1], is(nullValue()));

        Thread.sleep(100);
        controlQuality.distributeOnStorage(reproduced);
        assertThat(shop.getFoods()[1], is(reproduced));
    }

    /**
     * Additional warehouse.
     */
    @Test
    public void whenFirstWarehouseIsFullThenFillSecondWarehouse() {
        Storage warehouse = new Warehouse(1);
        Storage warehouseMoscow = new Warehouse(10);
        Storage shop = new Shop(10);
        Food apple0 = new Fruit("apple0", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 10, 100, 0);
        Food apple1 = new Fruit("apple1", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 10, 100, 0);
        Food[] expected = new Food[] {apple0};
        ControlQuality controlQuality = new ControlQuality(warehouse, warehouseMoscow, shop);
        controlQuality.distributeOnStorage(apple0);
        controlQuality.distributeOnStorage(apple1);
        assertThat(warehouse.getFoods(), is(expected));
        assertThat(warehouseMoscow.getFoods()[0], is(apple1));
    }

    /**
     * Set & get coverage.
     */
    @Test
    public void testGetAndSet() {
        Food apple = new Fruit("apple0", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 10, 100, 0);
        Food reproduced = new FoodReproducer(apple, true);

        reproduced.setName("fruit");
        assertThat(reproduced.getName(), is("fruit"));
        reproduced.setPrice(330);
        assertThat(reproduced.getPrice(), is(330.0));
        reproduced.setCreateDate(123);
        assertThat(reproduced.getCreateDate(), is(123L));
        reproduced.setExpireDate(567);
        assertThat(reproduced.getExpireDate(), is(567L));
    }
}