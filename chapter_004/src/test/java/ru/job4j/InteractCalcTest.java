package ru.job4j;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * InteractCalcTest class.
 *
 * @author Denis
 * @since 09.02.2017
 */
public class InteractCalcTest {
    /**
     * Input.
     */
    private InputCalc input;

    /**
     * Calculator.
     */
    private Calculator calculator;

    /**
     * Stub answers.
     */
    private String[] answers;

    /**
     * Menu calculator.
     */
    private MenuCalculator menuCalculator;

    /**
     * Menu size.
     */
    private static final int MENU_SIZE = 4;

    /**
     * Initialization.
     */
    @Before
    public void init() {
        this.calculator = new Calculator();
    }

    /**
     * Addition.
     */
    @Test
    public void whenAdditionOneAndTwoThenReturnThree() {
        this.answers = new String[] {"0", "1", "2", "", "0", "1", "2", "y"};
        this.input = new StubInputCalc(answers);
        this.menuCalculator = new MenuCalculator(this.input, this.calculator, MENU_SIZE);
        int[] menuRange = this.menuCalculator.fillAction();
        new InteractCalc(this.input, this.menuCalculator, menuRange).init();
        assertThat(this.calculator.getResult(), is(3.0));
    }

    /**
     * Subtraction.
     */
    @Test
    public void whenSubtractThreeAndOneThenReturnTwo() {
        this.answers = new String[] {"1", "3", "1", "y"};
        this.input = new StubInputCalc(this.answers);
        this.menuCalculator = new MenuCalculator(this.input, this.calculator, MENU_SIZE);
        int[] menuRange = this.menuCalculator.fillAction();
        new InteractCalc(this.input, this.menuCalculator, menuRange).init();
        assertThat(this.calculator.getResult(), is(2.0));
    }

    /**
     * Multiplication.
     */
    @Test
    public void whenMultiplyThreeAndOneThenReturnThree() {
        this.answers = new String[] {"2", "3", "1", "y"};
        this.input = new StubInputCalc(this.answers);
        this.menuCalculator = new MenuCalculator(this.input, this.calculator, MENU_SIZE);
        int[] menuRange = this.menuCalculator.fillAction();
        new InteractCalc(this.input, this.menuCalculator, menuRange).init();
        assertThat(this.calculator.getResult(), is(3.0));
    }

    /**
     * Division.
     */
    @Test
    public void whenDivideSixByTwoThenReturnTwo() {
        this.answers = new String[] {"3", "6", "2", "y"};
        this.input = new StubInputCalc(this.answers);
        this.menuCalculator = new MenuCalculator(this.input, this.calculator, MENU_SIZE);
        int[] menuRange = this.menuCalculator.fillAction();
        new InteractCalc(this.input, this.menuCalculator, menuRange).init();
        assertThat(this.calculator.getResult(), is(3.0));
    }
}