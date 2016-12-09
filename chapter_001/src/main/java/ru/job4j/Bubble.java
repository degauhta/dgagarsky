package ru.job4j;

/**
* Class Bubble. For bubble sort arrsy.
*/
public class Bubble {
	/**
	* Bubble sorting.
	* @param values not sorted array.
	* @return sorted array.
	*/
	public int[] bubbleSort(int[] values) {
		int temp;
        for (int i = values.length - 1; i > 0; i--) {
            for (int j = 0; j < values.length - 1; j++) {
                if (values[j + 1] < values[j]) {
                    temp = values[j + 1];
                    values[j + 1] = values[j];
                    values[j] = temp;
                }
            }
        }
		return values;
	}
}