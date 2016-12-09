package ru.job4j;

/**
* Class Rotation.
*/
public class Rotate {
	/**
	* Rotate square array.
	* @param values square array.
	* @return rotated array.
	*/
	public int[][] rotateSquareArray(int[][] values) {
	    int[][] result = new int[values.length][values.length];
        int k = values.length - 1;
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                result[j][k] = values[i][j];
            }
            k--;
        }
		return result;
	}
}