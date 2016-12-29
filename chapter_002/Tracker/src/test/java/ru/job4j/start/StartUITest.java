package ru.job4j.start;

import org.junit.Test;
import ru.job4j.models.Item;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

/**
 * StartUITest class.
 *
 * @author Denis
 * @since 27.12.2016
 */
public class StartUITest {
    /**
     * Test stub input.
     */
    @Test
    public void addTwoItemInTracker() {
        final int length = 2;
        Item[] itemArray = new Item[length];
        long createData = 1L;
        itemArray[0] = new Item("firstTestItem", "desc firstTestItem", createData);
        itemArray[1] = new Item("secondTestItem", "desc secondTestItem", createData);
        StubInput input = new StubInput(new String[]{"1", "firstTestItem", "desc firstTestItem",
                "1", "secondTestItem", "desc secondTestItem", //"6"});
                "6"});
        Tracker tracker = new Tracker();
        StartUI ui = new StartUI(input, tracker);
        ui.init();
        itemArray[0].setId(tracker.getAll()[0].getId());
        itemArray[1].setId(tracker.getAll()[1].getId());
        assertThat(tracker.getAll(), samePropertyValuesAs(itemArray));
    }

    /**
     * Testing edit.
     */
    @Test
    public void editItemInTracker() {
        final int length = 1;
        Item[] itemArray = new Item[length];
        long createData = 1L;
        itemArray[0] = new Item("firstTestItem-edit", "desc firstTestItem", createData);
        StubInput input = new StubInput(new String[]{"1", "firstTestItem", "desc firstTestItem",
                "2", "1", "firstTestItem-edit", "firstTestItem",
                "6"});
        Tracker tracker = new Tracker();
        StartUI ui = new StartUI(input, tracker);
        ui.init();
        itemArray[0].setId(tracker.getAll()[0].getId());
        assertThat(tracker.getAll(), samePropertyValuesAs(itemArray));
    }

    /**
     * Testing delete.
     */
    @Test
    public void deleteItemInTracker() {
        final int length = 1;
        Item[] itemArray = new Item[length];
        long createData = 1L;
        itemArray[0] = new Item("firstTestItem", "desc firstTestItem", createData);
        StubInput input = new StubInput(new String[]{"1", "firstTestItem", "desc firstTestItem",
                "1", "secondTestItem", "desc secondTestItem", //"6"});
                "3", "2",
                "6"});
        Tracker tracker = new Tracker();
        StartUI ui = new StartUI(input, tracker);
        ui.init();
        itemArray[0].setId(tracker.getAll()[0].getId());
        assertThat(tracker.getAll(), samePropertyValuesAs(itemArray));
    }

    /**
     * Testing filter.
     */
    @Test
    public void filterItemInTracker() {
        final int length = 2;
        Item[] itemArray = new Item[length];
        long createData = 1L;
        itemArray[0] = new Item("firstTestItem", "disc", createData);
        itemArray[1] = new Item("secondTestItem", "desc secondTestItem", createData);
        StubInput input = new StubInput(new String[]{"1", "firstTestItem", "disc",
                "1", "secondTestItem", "desc secondTestItem", //"6"});
                "5", "disc",
                "6"});
        Tracker tracker = new Tracker();
        StartUI ui = new StartUI(input, tracker);
        ui.init();
        itemArray[0].setId(tracker.getAll()[0].getId());
        assertThat(tracker.filterRequest("disc"), samePropertyValuesAs(itemArray));
    }
}