package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test class Merge.
* @author dgagarsky
* @since 11.12.2016
*/
public class MergeTest {
	/**
	* Testing merge two sorted array in one.
	*/
	@Test
	public void testMerging() {
		final int one = 1;
		final int two = 2;
		final int three = 3;
		final int four = 4;
		final int five = 5;
		final int six = 6;
		final int seven = 7;
		final int eight = 8;
		final int nine = 9;
		final int ten = 10;
		int[] firstArray = {one, two, three, four, five};
		int[] secondArray = {two, five, six, seven, eight, nine, ten};
		int[] expected = {one, two, two, three, four, five, five, six, seven, eight, nine, ten};
		Merge merge = new Merge();
		assertThat(merge.mergeArrays(firstArray, secondArray), is(expected));
	}
}