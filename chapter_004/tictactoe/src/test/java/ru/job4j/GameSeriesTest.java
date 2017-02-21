package ru.job4j;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * GameSeriesTest class.
 *
 * @author Denis
 * @since 18.02.2017
 */
public class GameSeriesTest {
    /**
     * Player first move and win (full row).
     */
    @Test
    public void whenPlayerFirstMoveAndWinRowThenReturnWin() {
        Board board = new GameBoard(3);
        int[] answers = new int[]{2, 0, 2, 1, 2, 2};
        Input input = new StubInput(answers);
        Game game = new GameSeries(input, true, board, 1);
        game.party();
        System.out.println(game.getResult());
        assertThat(game.getResult().toString(), is("Game 1 - player win."));
    }

    /**
     * PC first move and win (full row).
     */
    @Test
    public void whenPlayerSecondMoveAndLoseRowThenReturnLose() {
        Board board = new GameBoard(3);
        int[] answers = new int[]{2, 0, 2, 1, 2, 2};
        Input input = new StubInput(answers);
        Game game = new GameSeries(input, false, board, 1);
        game.party();
        System.out.println(game.getResult());
        assertThat(game.getResult().toString(), is("Game 1 - PC win."));
    }

    /**
     * Player first move and win (full column).
     */
    @Test
    public void whenPlayerFirstMoveAndWinColumnThenReturnWin() {
        Board board = new GameBoard(3);
        int[] answers = new int[]{0, 2, 1, 2, 2, 2};
        Input input = new StubInput(answers);
        Game game = new GameSeries(input, true, board, 1);
        game.party();
        System.out.println(game.getResult());
        assertThat(game.getResult().toString(), is("Game 1 - player win."));
    }

    /**
     * PC first move and win (full column).
     */
    @Test
    public void whenPlayerSecondMoveAndLoseColumnThenReturnLose() {
        Board board = new GameBoard(3);
        int[] answers = new int[]{1, 1, 1, 2, 2, 1};
        Input input = new StubInput(answers);
        Game game = new GameSeries(input, false, board, 1);
        game.party();
        System.out.println(game.getResult());
        assertThat(game.getResult().toString(), is("Game 1 - PC win."));
    }


    /**
     * Player first move and win (increasing diagonal).
     */
    @Test
    public void whenPlayerFirstMoveAndWinIncreasingDiagonalThenReturnWin() {
        Board board = new GameBoard(3);
        int[] answers = new int[]{0, 2, 1, 1, 2, 0};
        Input input = new StubInput(answers);
        Game game = new GameSeries(input, true, board, 1);
        game.party();
        System.out.println(game.getResult());
        assertThat(game.getResult().toString(), is("Game 1 - player win."));
    }

    /**
     * Player first move and win (decreasing diagonal).
     */
    @Test
    public void whenPlayerFirstMoveAndWinDecreasingDiagonalThenReturnWin() {
        Board board = new GameBoard(3);
        int[] answers = new int[]{0, 0, 1, 1, 2, 2};
        Input input = new StubInput(answers);
        Game game = new GameSeries(input, true, board, 1);
        game.party();
        System.out.println(game.getResult());
        assertThat(game.getResult().toString(), is("Game 1 - player win."));
    }

    /**
     * Player first move and 2 win.
     */
    @Test
    public void whenPlayerFirstMoveAndWinRowTwoGameThenReturnWin() {
        Board board = new GameBoard(3);
        int[] answers = new int[]{2, 0, 2, 1, 2, 2, 2, 0, 2, 1, 2, 2};
        Input input = new StubInput(answers);
        Game game = new GameSeries(input, true, board, 2);
        game.party();
        System.out.println(game.getResult());
        assertThat(game.getResult().toString(), is("Game 1 - player win.Game 2 - player win."));
    }

    /**
     * Player first move and draw.
     */
    @Test
    public void whenPlayerFirstMoveAndDrawThenReturnDraw() {
        Board board = new GameBoard(3);
        int[] answers = new int[]{0, 1, 1, 1, 1, 2, 2, 0, 2, 2};
        Input input = new StubInput(answers);
        Game game = new GameSeries(input, true, board, 1);
        game.party();
        System.out.println(game.getResult());
        assertThat(game.getResult().toString(), is("Game 1 - draw."));
    }
}