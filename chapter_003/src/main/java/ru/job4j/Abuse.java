package ru.job4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Abuse class.
 *
 * @author Denis
 * @since 18.01.2017
 */
public class Abuse {
    /**
     * Delete abuse words from stream.
     *
     * @param in    input stream.
     * @param out   output stream.
     * @param abuse array of abuse string.
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedInputStream bis = new BufferedInputStream(in);
             BufferedOutputStream bof = new BufferedOutputStream(out)) {
            int oneByte;
            int[] abuseCharFound = new int[abuse.length];
            byte[] buffer = new byte[32];
            int counter = 0;
            while ((oneByte = bis.read()) != -1) {
                if (counter > buffer.length) {
                    buffer = Arrays.copyOf(buffer, buffer.length * 2);
                }
                buffer[counter++] = (byte) oneByte;
                for (int i = 0; i < abuse.length; i++) {
                    if (abuse[i].charAt(abuseCharFound[i]) == oneByte) {
                        abuseCharFound[i]++;
                        if (abuseCharFound[i] == abuse[i].length()) {
                            abuseCharFound[i] = 0;
                            for (int j = counter - abuse[i].length(); j < counter; j++) {
                                buffer[j] = 0;
                            }
                            counter = counter - abuse[i].length();
                            break;
                        }
                    } else {
                        abuseCharFound[i] = 0;
                    }
                }
            }
            out.write(buffer, 0, counter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
