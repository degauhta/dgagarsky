package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test for class Bubble.
*/
public class BubbleTest {
	/**
	/**
	* Test sorting.
	*/
	@Test
	public void testBubbleSort() {
		final int zero = 0;
		final int one = 1;
		final int two = 2;
		final int three = 3;
		final int four = 4;
		final int five = 5;
		final int six = 6;
		final int seven = 7;
		final int eight = 8;
		final int nine = 9;
		Bubble bubble = new Bubble();
		int[] actual = {three, zero, one, eight, seven, two, five, four, six, nine};
		int[] excepted = {zero, one, two, three, four, five, six, seven, eight, nine};
		assertThat(bubble.bubbleSort(actual), is(excepted));
	}
}