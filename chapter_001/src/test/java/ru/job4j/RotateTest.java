package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test class Rotation.
*/
public class RotateTest {
	/**
	* Test rotation.
	*/
	@Test
	public void testRotationOfSquareArray() {
		final int one = 1;
		final int two = 2;
		final int three = 3;
		final int four = 4;
		final int five = 5;
		final int six = 6;
		final int seven = 7;
		final int eight = 8;
		final int nine = 9;
	    int[][] actual = {{one, two, three}, {four, five, six}, {seven, eight, nine}};
		int[][] expected = {{seven, four, one}, {eight, five, two}, {nine, six, three}};
		Rotate rotate = new Rotate();
        assertThat(rotate.rotateSquareArray(actual), is(expected));
	}
}