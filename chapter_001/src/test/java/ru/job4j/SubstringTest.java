package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test for class Substring.
*/
public class SubstringTest {
	/**
	* String contains substring.
	*/
	@Test
	public void whenStringContainsSubstring() {
        String origin = "qrwerty";
        String sub = "rt";
		Substring substring = new Substring();
        assertThat(substring.contains(origin, sub), is(true));
	}
	/**
	* String doesn't contains substring.
	*/
	@Test
	public void whenStringDoesntContainsSubstring() {
        String origin = "qrwtetry";
        String sub = "rt";
		Substring substring = new Substring();
        assertThat(substring.contains(origin, sub), is(false));
	}
}