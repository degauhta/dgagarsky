package ru.job4j;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * SortFile class.
 *
 * @author Denis
 * @since 19.01.2017
 */
public class SortFile {
    /**
     * Array of the line sizes.
     */
    private int[] lineSize;

    /**
     * Array of the line offset position.
     */
    private long[] linePosition;

    /**
     * Create sorted file.
     * @param source unsorted file.
     * @param distance sorted file.
     */
    public void sort(File source, File distance) {
        lineSize = new int[4];
        linePosition = new long[4];
        fillNotUseCellWithNegative(lineSize, linePosition, 0);
        String line;
        int count = 0;

        try (RandomAccessFile srcFile = new RandomAccessFile(source, "r");
             RandomAccessFile outFile = new RandomAccessFile(distance, "rw");) {
            do {
                line = srcFile.readLine();
                if (count == lineSize.length) {
                    grow();
                    fillNotUseCellWithNegative(lineSize, linePosition, count);
                }
                if (line != null) {
                    lineSize[count] = line.length();
                    linePosition[count] = srcFile.getFilePointer() - lineSize[count++]
                            - System.getProperty("line.separator").getBytes().length;
                }
            } while (line != null);
            linePosition[(count - 1)] += System.getProperty("line.separator").getBytes().length;

            lineSize = Arrays.copyOf(lineSize, (count));
            linePosition = Arrays.copyOf(linePosition, (count));

            sortArray(lineSize, linePosition);

            for (int i = 0; i < lineSize.length; i++) {
                srcFile.seek(linePosition[i]);
                line = srcFile.readLine();
                outFile.writeBytes(line);
                if (i < lineSize.length - 1) {
                    outFile.write(System.getProperty("line.separator").getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Increase current size of arrays by 2.
     */
    void grow() {
        lineSize = Arrays.copyOf(lineSize, lineSize.length * 2);
        linePosition = Arrays.copyOf(linePosition, linePosition.length * 2);
    }

    /**
     * Fills not use cells with -1.
     * @param ints array.
     * @param longs array.
     * @param start start position to fill.
     */
    void fillNotUseCellWithNegative(int[] ints, long[] longs, int start) {
        for (int i = start; i < ints.length; i++) {
            ints[i] = -1;
            longs[i] = -1;
        }
    }

    /**
     * Bubble sort of arrays.
     * @param lineSize array of the line sizes.
     * @param linePosition array of the line offset position.
     */
    void sortArray(int[] lineSize, long[] linePosition) {
        int tmp;
        long tmpLong;
        for (int i = lineSize.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (lineSize[j] > lineSize[j + 1]) {
                    tmp = lineSize[j + 1];
                    lineSize[j + 1] = lineSize[j];
                    lineSize[j] = tmp;

                    tmpLong = linePosition[j + 1];
                    linePosition[j + 1] = linePosition[j];
                    linePosition[j] = tmpLong;
                }
            }
        }
    }

    /**
     * Get array of the line sizes.
     * @return array.
     */
    public int[] getLineSize() {
        return lineSize;
    }
}
