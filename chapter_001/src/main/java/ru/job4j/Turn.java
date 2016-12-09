package ru.job4j;

/**
* Class Turn.
*/
public class Turn {
	/**
	* Revers array.
	*@param array that need to reverse.
	*@return reversing array.
	*/
	public int[] back(int[] array) {
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            array[i] = array[i] + array[length - i - 1];
            array[length - i - 1] = array[i] - array[length - i - 1];
            array[i] = array[i] - array[length - i - 1];
        }
        return array;
	}
}