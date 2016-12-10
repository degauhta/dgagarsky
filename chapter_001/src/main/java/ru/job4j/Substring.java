package ru.job4j;

/**
* Class Substring.
*/
public class Substring {
	/**
	* Check contains substring  in original string.
	* @param origin original string.
	* @param sub substring.
	* @return true if contains.
	*/
	public boolean contains(String origin, String sub) {
		char[] originArray = origin.toCharArray();
        char[] subArray = sub.toCharArray();
        boolean result = true;
        int index = 0;
        for (char letter : originArray) {
            if (subArray[index] == letter) {
                index++;
                result = true;
            } else {
                result = false;
                index = 0;
            }
            if (index == subArray.length) {
                break;
            }
        }
		return result;
	}
}