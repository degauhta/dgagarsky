package ru.job4j.start;

import java.util.Scanner;

/**
 * Console input class.
 *
 * @author Denis
 * @since 24.12.2016
 */
public class ConsoleInput implements Input {
    /**
     * Scanner.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Working with question.
     *
     * @param question .
     * @return answers.
     */
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
