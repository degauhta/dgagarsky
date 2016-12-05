package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test class Max.
* @author dgagarsky
* @since 05.12.2016
*/
public class MaxTest {
	/**
	* Numer 1.
	*/
	private final int one = 1;
	/**
	* Numer 2.
	*/
	private final int two = 2;
	/**
	* Numer 3.
	*/
	private final int three = 3;
	/**
	* Test maximum of two numbers.
	*/
	@Test
	public void testMaxOfTwoFirstNumber() {
		Max maximum = new Max();
		assertThat(maximum.max(two, one), is(two));
	}
	/**
	* Test maximum of two numbers.
	*/
	@Test
	public void testMaxOfTwoSecondNumber() {
		Max maximum = new Max();
		assertThat(maximum.max(one, two), is(two));
	}
	/**
	* Test maximum of three numbers.
	*/
	@Test
	public void testMaxOfThreeFirstNumber() {
		Max maximum = new Max();
		assertThat(maximum.max(three, one, two), is(three));
	}
		/**
	* Test maximum of three numbers.
	*/
	@Test
	public void testMaxOfThreeSecondNumber() {
		Max maximum = new Max();
		assertThat(maximum.max(one, three, two), is(three));
	}
		/**
	* Test maximum of three numbers.
	*/
	@Test
	public void testMaxOfThreeThirdNumber() {
		Max maximum = new Max();
		assertThat(maximum.max(one, two, three), is(three));
	}
}