package ru.job4j;

import java.util.Arrays;

/**
* Class Duplicate.
*/
public class Duplicate {
	/**
	* Delete duplicates in array.
	* @param values array with duplicates.
	* @return array without duplicates
	*/
	public String[] copyArray(String[] values) {
		int countOfDublicate = 0;
		int index = 1;
		String temp;
		String[] result = Arrays.copyOf(values, 1);
		while (countOfDublicate + index < values.length) {
			for (int i = 0; i < index; i++) {
				if (values[i].equals(values[index])) {
					temp = values[values.length - 1 - countOfDublicate];
					values[values.length - 1 - countOfDublicate] = values[index];
					values[index] = temp;
					countOfDublicate++;
				}
			}
			index++;
		}
		return Arrays.copyOf(values, values.length - countOfDublicate);
    }
}