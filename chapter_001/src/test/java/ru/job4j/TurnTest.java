package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test for class Turn.
*/
public class TurnTest {
	/**
	* Test for array lenght 3.
	*/
	@Test
	public void testTurnArrayLenghtThree() {
		final int one = 1;
		final int two = 2;
		final int three = 3;
		Turn turn = new Turn();
		int[] actual = {one, two, three};
		int[] expected = {three, two, one};
		assertThat(turn.back(actual), is(expected));
	}
	/**
	* Test for array lenght 6.
	*/
	@Test
	public void testTurnArrayLenghtSix() {
		final int one = 1;
		final int two = 2;
		final int three = 3;
		final int four = 4;
		final int five = 5;
		final int six = 6;
		Turn turn = new Turn();
		int[] actual = {one, two, three, four, five, six};
		int[] expected = {six, five, four, three, two, one};
		assertThat(turn.back(actual), is(expected));
	}
}