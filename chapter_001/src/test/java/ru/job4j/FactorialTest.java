package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test for class Factorial.
*/
public class FactorialTest {
	/**
	* Number -1.
	*/
	private final int negOne = -1;
	/**
	* Number 0.
	*/
	private final int zero = 0;
	/**
	* Number 1.
	*/
	private final int one = 1;
	/**
	* Number 5.
	*/
	private final int five = 5;
	/**
	* Number 120.
	*/
	private final int oneHundredTwenty = 120;
	/**
	* Test for negative numbers.
	*/
	@Test
	public void whenNegativeNumberThenZero() {
		Factorial fact = new Factorial();
		assertThat(fact.calculateFactorial(negOne), is(zero));
	}
	/**
	* Test for zero.
	*/
	@Test
	public void whenZeroThenOne() {
		Factorial fact = new Factorial();
		assertThat(fact.calculateFactorial(zero), is(one));
	}
	/**
	* Test for positive numbers.
	*/
	@Test
	public void whenPositiveNumber() {
		Factorial fact = new Factorial();
		assertThat(fact.calculateFactorial(five), is(oneHundredTwenty));
	}
}