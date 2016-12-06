package ru.job4j;

/**
* Class Max.
* @author dgagarsky
* @since 05.12.2016
*/
public class Max {
	/**
	* Find maximum of two numbers.
	* @param first first number.
	* @param second second number.
	* @return maximum.
	*/
	public int max(int first, int second) {
		return first >= second ? first : second;
	}
	/**
	* Find maximum of three numbers.
	* @param first first number.
	* @param second second number.
	* @param third second number.
	* @return maximum.
	*/
	public int max(int first, int second, int third) {
		return max(max(first, second), third);
	}
}