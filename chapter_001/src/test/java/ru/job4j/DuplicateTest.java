package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test for class Duplicate.
*/
public class DuplicateTest {
	/**
	* Test method copyArray.
	*/
	@Test
	public void copyArray() {
		String[] actual = {"hello", "world1", "denis", "hello", "world", "hello", ".", "den", "denis"};
		String[] expected = {"hello", "world1", "denis", "den", "world", "."};
		Duplicate duplicate = new Duplicate();
		assertThat(duplicate.copyArray(actual), is(expected));
    }
}