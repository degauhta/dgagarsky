package ru.job4j;

import java.io.IOException;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.Random;

/**
 * Chat class.
 *
 * @author Denis
 * @since 21.01.2017
 */
class Chat {
    /**
     * Input.
     */
    private Input input;

    /**
     * Constructor.
     * @param input input.
     */
    Chat(Input input) {
        this.input = input;
    }

    /**
     * Start chat with bot. Read file for bot logic.
     * @param chatFile bot chat phrases.
     * @param logFile console log.
     */
    void startChat(File chatFile, File logFile) {
        int count = 0;
        long[] linePositions = new long[32];

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(chatFile, "r");
             Writer outFile = new OutputStreamWriter(new FileOutputStream(logFile))) {
            String line;

            do {
                linePositions[count++] = randomAccessFile.getFilePointer();
                line = randomAccessFile.readLine();
                if (count == linePositions.length) {
                    linePositions = Arrays.copyOf(linePositions, linePositions.length * 2);
                }
                if (line == null) {
                    linePositions[--count] = 0;
                }
            } while (line != null);


            chat(randomAccessFile, outFile, count, linePositions);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Chat with bot.
     * @param randomAccessFile bot chat phrases.
     * @param outFile log.
     * @param count of string in bot file phrases.
     * @param linePositions offset in bot file phrases.
     * @throws IOException possible exception.
     */
    private void chat(RandomAccessFile randomAccessFile, Writer outFile, int count, long[] linePositions) throws IOException {
        boolean botActive = true;
        String phrase;
        String userPhrase;
        Random random = new Random();
        do {
            if (botActive) {
                randomAccessFile.seek(linePositions[random.nextInt(count)]);
                phrase = "Bot: " + randomAccessFile.readLine();
                System.out.println(phrase);
                outFile.write(phrase);
                outFile.write(System.getProperty("line.separator"));
            }

            System.out.print("User: ");
            userPhrase = input.chat();
            phrase = "User: " + userPhrase;
            outFile.write(phrase);
            outFile.write(System.getProperty("line.separator"));

            if ("continue".equals(userPhrase)) {
                botActive = true;
            } else if ("pause".equals(userPhrase)) {
                botActive = false;
            }
        } while (!"quit".equals(userPhrase));
    }
}
