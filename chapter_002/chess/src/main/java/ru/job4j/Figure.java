package ru.job4j;

import java.lang.reflect.Constructor;

/**
 * Figure abstract class.
 *
 * @author Denis
 * @since 05.01.2017
 */
public abstract class Figure {
    /**
     * Position of figure.
     */
    private final Cell position;

    /**
     * Constructor.
     * @param position of figure.
     */
    public Figure(Cell position) {
        this.position = position;
    }

    /**
     * Move figure to destination cell.
     * @param dest destination.
     * @param cells array of cell.
     * @return if movement possible - array of cells that figure go.
     * @throws ImpossibleMoveException if movement not possible.
     */
    public abstract Cell[] way(Cell dest, Cell[][] cells) throws ImpossibleMoveException;
    /*
    Метод должен работать так.
    dist - задают ячейку куда следует пойти.
    Если фигура может туда пойти. то Вернуть массив ячеек. которые должна пройти фигура.
    Если фигура туда пойти не может. выбросить исключение ImpossibleMoveException
    */

    /**
     * Clone figure to square.
     * @param dest destination.
     * @param className class name of new figure.
     * @return clone figure.
     */
    public Figure clone(Cell dest, String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Constructor constructor = clazz.getConstructor(Cell.class);
            Object cloneFigure = constructor.newInstance(dest);
            return (Figure) cloneFigure;
        } catch (Exception  e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get position of figure.
     * @return position.
     */
    public Cell getPosition() {
        return this.position;
    }
}
