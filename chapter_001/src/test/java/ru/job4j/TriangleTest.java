package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
* Test class Triangle.
* @author dgagarsky
* @since 04.12.2016
*/
public class TriangleTest {
	/**
	 * Number 0.
	*/
	private final double zero = 0.0;
	/**
	 * Number 1.0.
	*/
	private final double one = 1.0;
	/**
	 * Number 5.0.
	*/
	private final double five = 5.0;
	/**
	 * Number 8.0.
	*/
	private final double eight = 8.0;
	/**
	 * Error 0.01.
	*/
	private final double error = 8.01;
	/**
	* Test triangle.
	*/
	@Test
	public void checkAreaOfTriangle() {
		Point a = new Point(one, one);
		Point b = new Point(one, five);
		Point c = new Point(five, one);
		Triangle triangle = new Triangle(a, b, c);
		assertThat(triangle.area(), closeTo(eight, error));
	}
	/**
	* Test not triangle.
	*/
	@Test
	public void checkAreaOfNotTriangle() {
		Point a = new Point(one, one);
		Point b = new Point(five, one);
		Point c = new Point(five, one);
		Triangle triangle = new Triangle(a, b, c);
		assertThat(triangle.area(), is(zero));
	}

}