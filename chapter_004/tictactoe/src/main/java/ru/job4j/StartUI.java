package ru.job4j;

/**
 * StartUI class.
 *
 * @author Denis
 * @since 20.02.2017
 */
public class StartUI {
    /**
     * Input.
     */
    private Input input;

    /**
     * MAin constructor.
     *
     * @param input input.
     */
    public StartUI(Input input) {
        this.input = input;
    }

    /**
     * Play.
     */
    private void play() {
        boolean userPutFirst;
        int size;
        Board board;
        int countMatches;
        Game game;
        do {
            clearConsole();
            userPutFirst = input.ask("Who put sign first? (1 - player, any other number - PC)") == 1;
            size = input.askOdd("Enter game board size (odd number > 1):");
            board = new GameBoard(size);
            countMatches = input.ask("Enter number of matches in game series:");
            game = new GameSeries(input, userPutFirst, board, countMatches);
            game.party();
            System.out.println(game.getResult());
        } while (1 != this.input.ask("Exit? (1):"));
    }

    /**
     * Clear console.
     */
    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Main method.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        Input input = new ValidateInput();
        new StartUI(input).play();
    }
}
