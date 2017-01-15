package ru.job4j;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * EvenNumber class.
 *
 * @author Denis
 * @since 14.01.2017
 */
public class EvenNumber {
    /**
     * Check if in InputStream for even number.
     * @param in byte input stream.
     * @return true if there is even number.
     */
    public boolean isNumber(InputStream in) {
        boolean result = false;
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(in)) {
            int number;
            while ((number = bufferedInputStream.read()) != -1) {
                if (number % 2 == 0) {
                    result = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
