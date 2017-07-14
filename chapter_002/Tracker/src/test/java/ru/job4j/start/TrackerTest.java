package ru.job4j.start;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Bug;
import ru.job4j.models.Item;
import ru.job4j.models.Task;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Denis on 19.12.2016.
 */
public class TrackerTest {
    /**
     * Empty tracker.
     */
    private TrackerInMemory trackerEmpty;
    /**
     * TrackerInMemory with three items.
     */
    private TrackerInMemory trackerFull;
    /**
     * First test item.
     */
    private Item firstTestItem;
    /**
     * Second test item.
     */
    private Item secondTestItem;
    /**
     * Third test item.
     */
    private Item thirdTestItem;
    /**
     * Array of items.
     */
    private Item[] itemArray;
    /**
     * Create data.
     */
    private long createData;

    /**
     * Initializing variables.
     * @throws Exception .
     */
    @Before
    public void setUp() throws Exception {
        createData = 1L;
        trackerEmpty = new TrackerInMemory();
        firstTestItem = new Item("firstTestItem", "desc firstTestItem", createData);
        secondTestItem = new Task("secondTestItem", "desc secondTestItem", createData);
        thirdTestItem = new Bug("thirdTestItem", "desc thirdTestItem", createData);
        trackerFull = new TrackerInMemory();
        Item item = trackerFull.add(firstTestItem);
        firstTestItem.setId(item.getId());
        item = trackerFull.add(secondTestItem);
        secondTestItem.setId(item.getId());
        item = trackerFull.add(thirdTestItem);
        thirdTestItem.setId(item.getId());
    }

    /**
     * Test for add method.
     * @throws Exception .
     */
    @Test
    public void add() throws Exception {
        Item actual = trackerEmpty.add(new Item("firstTestItem", "desc firstTestItem", createData));
        firstTestItem.setId(actual.getId());
        assertThat(actual, samePropertyValuesAs(firstTestItem));
        actual = trackerEmpty.add(new Task("secondTestItem", "desc secondTestItem", createData));
        secondTestItem.setId(actual.getId());
        assertThat(actual, samePropertyValuesAs(secondTestItem));
        actual = trackerEmpty.add(new Bug("thirdTestItem", "desc thirdTestItem", createData));
        thirdTestItem.setId(actual.getId());
        assertThat(actual, samePropertyValuesAs(thirdTestItem));
    }

    /**
     * Test for filter method.
     * @throws Exception .
     */
    @Test
    public void filterRequest() throws Exception {
        itemArray = new Item[2];
        itemArray[0] = secondTestItem;
        itemArray[1] = thirdTestItem;
        Item[] actual = trackerFull.filterRequest("dtest");
        assertArrayEquals(actual, itemArray);
    }

    /**
     * Testing getAll method.
     * @throws Exception .
     */
    @Test
    public void getAll() throws Exception {
        final int length = 3;
        itemArray = new Item[length];
        itemArray[0] = firstTestItem;
        itemArray[1] = secondTestItem;
        itemArray[2] = thirdTestItem;
        Item[] actual = trackerFull.getAll();
        assertArrayEquals(actual, itemArray);
    }

    /**
     * Test for edit method.
     * @throws Exception .
     */
    @Test
    public void editRequest() throws Exception {
        Item actual = new Item("newName", "newDescription", createData);
        actual.setId(secondTestItem.getId());
        actual = trackerFull.editRequest(actual);
        Item expected = new Item("newName", "newDescription", createData);
        expected.setId(actual.getId());
        assertThat(actual, samePropertyValuesAs(expected));
    }

    /**
     * test for delete method.
     * @throws Exception .
     */
    @Test
    public void removeRequest() throws Exception {
        assertTrue(trackerFull.removeRequest(thirdTestItem));
    }

}