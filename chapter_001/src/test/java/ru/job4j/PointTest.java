package ru.job4j;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
* Test class Point.
* @author dgagarsky
* @since 04.12.2016
*/
public class PointTest {
	/**
	 * Number 1.0.
	*/
	private final double one = 1.0;
	/**
	 * Number 2.0.
	*/
	private final double two = 2.0;
	/**
	 * Number 4.0.
	*/
	private final double four = 4.0;
	/**
	 * Number 5.0.
	*/
	private final double five = 5.0;
	/**
	 * Error 0.01.
	*/
	private final double error = 8.01;
	/**
	* Test calculation.
	*/
	@Test
	public void checkCalculationDistanceTo() {
		Point pointA = new Point(one, two);
		Point pointB = new Point(five, two);
		assertThat(pointA.distanceTo(pointB), closeTo(four, error));
	}
}