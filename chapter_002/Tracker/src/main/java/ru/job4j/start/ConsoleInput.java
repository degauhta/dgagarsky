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

    /**
     * Select menu item.
     * @param question .
     * @param range of menu items.
     * @return user choose.
     */
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of choose range.");
        }
    }
}
