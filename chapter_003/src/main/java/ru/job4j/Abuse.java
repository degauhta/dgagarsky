package ru.job4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

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
        try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter bufWriter = new BufferedWriter(new OutputStreamWriter(out))) {
            StringBuilder regX = new StringBuilder();
            for (String s : abuse) {
                regX.append(s);
                regX.append("|");
            }
            String line;
            while ((line = bufReader.readLine()) != null) {
                line = line.replaceAll(regX.toString(), "");
                bufWriter.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
