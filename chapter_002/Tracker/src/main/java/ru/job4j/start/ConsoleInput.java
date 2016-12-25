package ru.job4j.start;

import java.util.Scanner;

/**
 * Console input class.
 *
 * @author Denis
 * @since 24.12.2016
 */
public class ConsoleInput {
    /**
     * Scanner.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Working with menu.
     *
     * @param menu string array menu.
     * @return user choose.
     */
    public int chooseMenu(String[] menu) {
        int result = -1;
        for (String menuLine : menu) {
            System.out.println(menuLine);
        }
        do {
            System.out.println("Print number of choose");
            result = Integer.parseInt(scanner.nextLine());
        } while (result < 1 || result > menu.length);
        return result;
    }

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
