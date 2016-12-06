package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test class Counter.
*/
public class CounterTest {
	/**
	* Number 1.
	*/
	private final int one = 1;
	/**
	* Number 9.
	*/
	private final int ten = 10;
	/**
	* Number 20.
	*/
	private final int twenty = 20;
	/**
	* Test loop.
	*/
	@Test
	public void testLoop() {
		Counter counter = new Counter();
		assertThat(counter.add(one, ten), is(twenty));
	}
}