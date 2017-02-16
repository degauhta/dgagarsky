package ru.job4j;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * MenuItemTest class.
 *
 * @author Denis
 * @since 15.02.2017
 */
public class MenuItemTest {
    /**
     * Show menu.
     */
    @Test
    public void whenShowMenuThenOutMenu() {
        Item item111 = new Item(111, "Item 1.1.1");
        Item item112 = new Item(112, "Item 1.1.2");
        Item item11 = new Item(11, "Item 1.1", item111, item112);
        Item item12 = new Item(12, "Item 1.2");
        Item item1 = new Item(1, "Item 1", item11, item12);
        Item item2 = new Item(2, "Item 2");
        MenuItem menu = new MenuItem(item1, item2);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String expected = Joiner.on(System.lineSeparator()).join("Item 1", "--Item 1.1",
                "----Item 1.1.1", "----Item 1.1.2", "--Item 1.2", "Item 2", "");
        menu.show(menu.getItems(), "");
        assertThat(outputStream.toString(), is(expected));
    }

    /**
     * Execute menu item.
     */
    @Test
    public void whenExecuteThenGetMessage() {
        Item item1 = new Item(1, "Item 1");
        String expected = "Execution Item 1";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        MenuItem menu = new MenuItem(item1);
        menu.execute(0);
        assertThat(outputStream.toString(), is(expected));
    }
}