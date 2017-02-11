
package ru.job4j;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * MenuCalculatorTrigonometricTest class.
 *
 * @author Denis
 * @since 11.02.2017
 */
public class MenuCalculatorTrigonometricTest {
    /**
     * Input.
     */
    private InputCalc input;

    /**
     * Calculator.
     */
    private CalculatorTrigonometric calculator;

    /**
     * Stub answers.
     */
    private String[] answers;

    /**
     * Menu calculator.
     */
    private MenuCalculator menuCalculator;

    /**
     * Initialization.
     */
    @Before
    public void init() {
        this.calculator = new CalculatorTrigonometric();
    }

    /**
     * Addition.
     */
    @Test
    public void whenAdditionOneAndTwoThenReturnThree() {
        this.answers = new String[]{"0", "1", "2", "", "0", "1", "2", "y"};
        this.input = new StubInputCalc(answers);
        this.menuCalculator = new MenuCalculatorTrigonometric(this.input, this.calculator, InteractCalc.MENU_SIZE);
        int[] menuRange = this.menuCalculator.fillAction();
        new InteractCalc(this.input, this.menuCalculator, menuRange).init();
        assertThat(this.calculator.getResult(), is(3.0));
    }

    /**
     * Subtraction.
     */
    @Test
    public void whenSubtractThreeAndOneThenReturnTwo() {
        this.answers = new String[]{"1", "3", "1", "y"};
        this.input = new StubInputCalc(this.answers);
        this.menuCalculator = new MenuCalculatorTrigonometric(this.input, this.calculator, InteractCalc.MENU_SIZE);
        int[] menuRange = this.menuCalculator.fillAction();
        new InteractCalc(this.input, this.menuCalculator, menuRange).init();
        assertThat(this.calculator.getResult(), is(2.0));
    }

    /**
     * Multiplication.
     */
    @Test
    public void whenMultiplyThreeAndOneThenReturnThree() {
        this.answers = new String[]{"2", "3", "1", "y"};
        this.input = new StubInputCalc(this.answers);
        this.menuCalculator = new MenuCalculatorTrigonometric(this.input, this.calculator, InteractCalc.MENU_SIZE);
        int[] menuRange = this.menuCalculator.fillAction();
        new InteractCalc(this.input, this.menuCalculator, menuRange).init();
        assertThat(this.calculator.getResult(), is(3.0));
    }

    /**
     * Division.
     */
    @Test
    public void whenDivideSixByTwoThenReturnTwo() {
        this.answers = new String[]{"3", "6", "2", "y"};
        this.input = new StubInputCalc(this.answers);
        this.menuCalculator = new MenuCalculatorTrigonometric(this.input, this.calculator, InteractCalc.MENU_SIZE);
        int[] menuRange = this.menuCalculator.fillAction();
        new InteractCalc(this.input, this.menuCalculator, menuRange).init();
        assertThat(this.calculator.getResult(), is(3.0));
    }

    /**
     * Sines.
     */
    @Test
    public void whenSin90ThenReturn1() {
        this.answers = new String[]{"4", "90", "y"};
        this.input = new StubInputCalc(this.answers);
        this.menuCalculator = new MenuCalculatorTrigonometric(this.input, this.calculator, InteractCalc.MENU_SIZE);
        int[] menuRange = this.menuCalculator.fillAction();
        new InteractCalc(this.input, this.menuCalculator, menuRange).init();
        assertThat(this.calculator.getResult(), is(closeTo(1.0, 0.01)));
    }

    /**
     * Cosines.
     */
    @Test
    public void whenCos90ThenReturn0() {
        this.answers = new String[]{"5", "90", "y"};
        this.input = new StubInputCalc(this.answers);
        this.menuCalculator = new MenuCalculatorTrigonometric(this.input, this.calculator, InteractCalc.MENU_SIZE);
        int[] menuRange = this.menuCalculator.fillAction();
        new InteractCalc(this.input, this.menuCalculator, menuRange).init();
        assertThat(this.calculator.getResult(), is(closeTo(0.0, 0.01)));
    }

}