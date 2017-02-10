package ru.job4j;

import ru.job4j.start.MenuOutException;

import java.util.Scanner;

/**
 * ConsoleInputCalc class.
 *
 * @author Denis
 * @since 08.02.2017
 */
public class ConsoleInputCalc implements InputCalc {
    /**
     * Scanner.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Working with question.
     *
     * @param question question
     * @return answers.
     * @throws NumberFormatException error.
     */
    public double askDouble(String question) throws NumberFormatException {
        System.out.println(question);
        return Double.parseDouble(scanner.nextLine());
    }

    /**
     * Working with question.
     *
     * @param question question.
     * @return answers.
     */
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Select menu item.
     *
     * @param question .
     * @param range    of menu items.
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
