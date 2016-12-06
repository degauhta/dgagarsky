package ru.job4j;

/**
* Class counter.
*/
public class Counter {
	/**
	* Summation even numbers in loop.
	* @param start start number.
	* @param finish last number.
	* @return result.
	*/
	public int add(int start, int finish) {
		int result = 0;
		for (int i = start; i < finish; i++) {
			result += i % 2 == 0 ? i : 0;
		}
		return result;
	}
}