package ru.job4j;

/**
 *Class Calculator.
 *@author dgagarsky
 *@since 03.12.2016
*/
public class Calculator {
	/**
	 * The result of calculation.
	*/
	private double result;
	/**
	* Summation of numbers, write in field result.
	*@param first the first number.
	*@param second the second number
	*/
	public void add(double first, double second) {
		this.result = first + second;
	}
	/**
	* Subtraction  of numbers, write in field result.
	*@param first the first number.
	*@param second the second number
	*/
	public void substruct(double first, double second) {
		this.result = first - second;
	}
	/**
	* Multiplication of numbers, write in field result.
	*@param first the first number.
	*@param second the second number
	*/
	public void multiple(double first, double second) {
		this.result = first * second;
	}
	/**
	* Division of numbers, write in field result.
	*@param first the first number.
	*@param second the second number.
	*/
	public void div(double first, double second) {
			this.result = first / second;
	}
	/**
	* Get field result.
	*@return field result
	*/
	public double getResult() {
		return result;
	}
}