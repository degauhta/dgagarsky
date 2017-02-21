package ru.job4j;

import java.util.Scanner;

/**
 * ConsoleInput class.
 *
 * @author Denis
 * @since 19.02.2017
 */
public class ConsoleInput implements Input {
    /**
     * Scanner.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Ask int.
     *
     * @param question to ask.
     * @return answer.
     */
    @Override
    public int ask(String question) {
        System.out.println(question);
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * Ask odd int.
     *
     * @param question to ask.
     * @return answer.
     */
    @Override
    public int askOdd(String question) {
        System.out.println(question);
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * Ask int contained in array.
     *
     * @param question question.
     * @param range of menu items.
     * @return user choose.
     */
    @Override
    public int ask(String question, int[] range) {
        boolean exist = false;
        int key = this.ask(question);
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new OutOfArrayException("Out of array.");
        }
    }
}
