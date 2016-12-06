package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test for class Paint.
*/
public class PaintTest {
	/**
	* Number 1.
	*/
	private final int one = 1;
	/**
	* Number 2.
	*/
	private final int two = 2;
	/**
	* Number 3.
	*/
	private final int three = 3;
	/**
	* Test pyramid winth hight = 1.
	*/
	@Test
	public void testPyramidOne() {
		String expected = "^";
		Paint paint = new Paint();
		assertThat(paint.printPyramid(one), is(expected));
	}
	/**
	* Test pyramid winth hight = 2.
	*/
	@Test
	public void testPyramidTwo() {
		String expected = " ^" + "\n" + "^ ^";
		Paint paint = new Paint();
		assertThat(paint.printPyramid(two), is(expected));
	}
	/**
	* Test pyramid winth hight = 3.
	*/
	@Test
	public void testPyramidThree() {
		String expected = "  ^" + "\n" + " ^ ^" + "\n" + "^   ^";
		Paint paint = new Paint();
		assertThat(paint.printPyramid(three), is(expected));
	}
}