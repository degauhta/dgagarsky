package ru.job4j;

/**
* Class factorial.
*/
public class Factorial {
	/**
	* Factorial calculation.
	* @param number positive number.
	* @return result.
	*/
	public int calculateFactorial(int number) {
		int result = 1;
		if (number > 0) {
			for (int i = 1; i <= number; i++) {
				result *= i;
			}
		} else {
			result = number == 0 ? 1 : 0;
		}
		return result;
	}
}