package ru.job4j;

/**
 * Algo array distance class.
 *
 * @author Denis
 * @since 05.02.2017
 */
public class AlgoArrayDistance {
    /**
     *  Finding max distance between same number in array.
     * @param array array of numbers.
     * @return max distance or -1 if array doesn't have same number.
     */
    public int findMaxDistanceBetweenSameNumber(int[] array) {
        int result = -1;
        if (array.length > 1) {
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[i] == array[j]) {
                        result = result > j - i ? result : j - i;
                    }
                }
            }
        }
        return result;
    }
}
