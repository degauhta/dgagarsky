package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class Calculator.
 * @author dgagarsky
 * @since 03.12.2016
*/
public class CalculatorTest {
	/**
	 * Number 0.0.
	*/
	private final double zero = 0.0;
	/**
	 * Number 1.0.
	*/
	private final double one = 1.0;
	/**
	 * Number 2.0.
	*/
	private final double two = 2.0;
	/**
	 * Number 3.0.
	*/
	private final double three = 3.0;
	/**
	 * Number 6.0.
	*/
	private final double six = 6.0;
	/**
	 * Test addition.
	*/
	@Test
	public void testingAddition() {
		Calculator calculator = new Calculator();
		calculator.add(one, two);
		assertThat(calculator.getResult(), is(three));
	}
	/**
	 * Test subtraction.
	*/
	@Test
	public void testingSubtraction() {
		Calculator calculator = new Calculator();
		calculator.substruct(three, two);
		assertThat(calculator.getResult(), is(one));
	}
	/**
	 * Test multiplication.
	*/
	@Test
	public void testingMultiplication() {
		Calculator calculator = new Calculator();
		calculator.multiple(two, three);
		assertThat(calculator.getResult(), is(six));
	}
	/**
	 * Test division. Double.POSITIVE_INFINITY
	*/
	@Test
	public void testingDivision() {
		Calculator calculator = new Calculator();
		calculator.div(six, two);
		assertThat(calculator.getResult(), is(three));
	}
	/**
	 * Test division by zero.
	*/
	@Test
	public void testingDivisionByZero() {
		Calculator calculator = new Calculator();
		calculator.div(six, zero);
		assertThat(calculator.getResult(), is(Double.POSITIVE_INFINITY));
	}
}