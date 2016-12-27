package ru.job4j.start;

import org.junit.Test;

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
    public void init() {
        Input input = new StubInput(new String[]{"1", "1st", "1disc",
                "1", "2nd", "2disc",
                "2", "2", "2nd", "second disc",
                "5", "disc",
                "3", "2",
                "4",
                "6"});
        new StartUI(input).init();
    }
}