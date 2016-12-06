package ru.job4j;

/**
* Class Paint.
*/
public class Paint {
	/**
	* Drawing pyramid.
	* @param h height of pyramid.
	* @return pyramid pseudographics.
	*/
	public String printPyramid(int h) {
		StringBuilder sb = new StringBuilder();
		int numberOfSpace = 1;
		for (int i = 1; i <= h; i++) {
			for (int j = 1; j <= (h - i); j++) {
				sb.append(" ");
			}
			sb.append("^");
			if (i != 1) {
				for (int j = 1; j <= numberOfSpace; j++) {
					sb.append(" ");
				}
				sb.append("^");
				numberOfSpace += 2;
			}
            if (i != h) {
                sb.append("\n");
            }
		}
		return sb.toString();
	}
}