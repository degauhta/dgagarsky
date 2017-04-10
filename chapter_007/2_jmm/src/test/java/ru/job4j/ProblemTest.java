package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

/**
 * ProblemTest class.
 *
 * @author Denis
 * @since 08.04.2017
 */
public class ProblemTest {
    /**
     * Test problem with jmm.
     */
    @Test
    public void whenIncreaseCounterXThenReturnNot2X() {
        Problem problem = new Problem(50_000_000);
        problem.counter();
        assertThat(problem.getShared(), not(100_000_000));
    }
}