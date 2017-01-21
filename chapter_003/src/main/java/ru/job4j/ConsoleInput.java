package ru.job4j;

import java.util.Scanner;

/**
 * ConsoleInput class.
 *
 * @author Denis
 * @since 21.01.2017
 */
class ConsoleInput implements Input {
    /**
     * Scanner.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Chat phrase.
     *
     * @return answer.
     */
    @Override
    public String chat() {
        return scanner.nextLine();
    }
}
