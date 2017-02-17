package ru.job4j;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * ControlQualityTest class.
 *
 * @author Denis
 * @since 17.02.2017
 */
public class ControlQualityTest {
    /**
     * Resort food.
     *
     * @throws InterruptedException InterruptedException error.
     */
    @Test
    public void whenResortFoodInStorageThenSameAmountOfFoodNotChangeInStorage() throws InterruptedException {
        Storage shop = new Shop(10);
        Storage warehouse = new Warehouse(10);
        Storage trash = new Trash(10);
        Food freshFruit = new Fruit("freshFruit", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 10, 100, 0);
        Food freshVegetable = new Vegetable("freshVegetable", System.currentTimeMillis() - 1000,
                System.currentTimeMillis() + 1000 * 10, 100, 0);
        Food fruit = new Fruit("fruit", System.currentTimeMillis() - 500,
                System.currentTimeMillis() + 1000, 100, 0);
        Food vegetable = new Vegetable("vegetable", System.currentTimeMillis() - 500,
                System.currentTimeMillis() + 1000, 100, 0);
        Food oldMeat = new Meat("oldMeat", System.currentTimeMillis() - 50,
                System.currentTimeMillis() + 100, 100, 0);
        Food meat = new Meat("meat", System.currentTimeMillis() - 50,
                System.currentTimeMillis() + 100, 100, 0);
        meat = new FoodReproducer(meat, true);
        ControlQuality controlQuality = new ControlQuality(warehouse, shop, trash);
        controlQuality.distributeOnStorage(freshFruit, freshVegetable, fruit, vegetable, meat, oldMeat);
        assertThat(warehouse.getFoods()[0], is(freshFruit));
        assertThat(warehouse.getFoods()[1], is(freshVegetable));
        assertThat(shop.getFoods()[0], is(fruit));
        assertThat(shop.getFoods()[1], is(vegetable));
        assertThat(shop.getFoods()[2], is(meat));
        assertThat(shop.getFoods()[3], is(oldMeat));
        Thread.sleep(200);
        controlQuality.resort();
        assertThat(warehouse.getFoods()[0], is(freshFruit));
        assertThat(warehouse.getFoods()[1], is(freshVegetable));
        assertThat(shop.getFoods()[0], is(fruit));
        assertThat(shop.getFoods()[1], is(vegetable));
        assertThat(shop.getFoods()[2], is(meat));
        assertThat(trash.getFoods()[0], is(oldMeat));
    }
}