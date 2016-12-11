package ru.job4j;

/**
* Class Merge.
*/
public class Merge {
	/**
	* Merge two sorted array in one.
	* @param firstArray first sorted array.
	* @param secondArray second sorted array.
	* @return result of merge.
	*/
	public int[] mergeArrays(int[] firstArray, int[]secondArray) {
        int[] result = new int[firstArray.length + secondArray.length];
        int indexFirst = 0;
        int indexSecond = 0;
        int indexResult = 0;
        while (indexFirst < firstArray.length | indexSecond < secondArray.length) {
            if (indexFirst == firstArray.length) {
                result[indexResult] = secondArray[indexSecond];
                indexSecond++;
                indexResult++;
                continue;
            }
            if (indexSecond == secondArray.length) {
                result[indexResult] = firstArray[indexFirst];
                indexFirst++;
                indexResult++;
                continue;
            }
            if (firstArray[indexFirst] < secondArray[indexSecond]) {
                result[indexResult] = firstArray[indexFirst];
                indexFirst++;
            } else if (firstArray[indexFirst] < secondArray[indexSecond]) {
                result[indexResult] = secondArray[indexSecond];
                indexSecond++;
            } else {
                result[indexResult] = firstArray[indexFirst];
                indexResult++;
                result[indexResult] = secondArray[indexSecond];
                indexFirst++;
                indexSecond++;
            }
            indexResult++;
        }
        return result;
	}
}