package ru.job4j;

import org.junit.Test;

/**
 * FieldTest class.
 *
 * @author Denis
 * @since 12.05.2017
 */
public class FieldTest {
    /**
     * Test generated empty field.
     */
    @Test
    public void testCreateEmptyField() {
        int row = 2;
        int column = 7;
        Field field = new Field(row, column, false, 0);
        Cell[][] cells = field.getCells();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.printf("%s ", cells[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Test generated field with walls.
     */
    @Test
    public void testCreateGameWithWalls() {
        int row = 3;
        int column = 7;
        Field field = new Field(row, column, true, 0);
        Cell[][] cells = field.getCells();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.printf("%s ", cells[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Test filed with 1 wall and 1 monster.
     */
    @Test
    public void testFieldWith1Wall2Monster() {
        Cell cell00 = new Cell(0, 0);
        FixedGameObject land00 = new Land(cell00);
        cell00.setGameObject(land00);

        Cell cell01 = new Cell(0, 1);
        FixedGameObject wall01 = new Wall(cell01);
        cell01.setGameObject(wall01);

        Cell cell02 = new Cell(0, 2);
        FixedGameObject land02 = new Land(cell02);
        cell02.setGameObject(land02);

        Cell cell03 = new Cell(0, 3);
        FixedGameObject land03 = new Land(cell03);
        cell03.setGameObject(land03);

        Cell cell04 = new Cell(0, 4);
        Monster monster04 = new Monster(cell04);
        cell04.setGameObject(monster04);

        Cell[][] cells = new Cell[][]{{cell00, cell01, cell02, cell03, cell04}};
        Monster[] monsters = new Monster[]{monster04};

        Field field = new Field(cells, monsters);

        field.start();
    }

    /**
     * Test filed with 2 monsters.
     */
    @Test
    public void testFieldWith2Monster() {
        Cell cell00 = new Cell(0, 0);
        Monster monster00 = new Monster(cell00);
        cell00.setGameObject(monster00);

        Cell cell01 = new Cell(0, 1);
        FixedGameObject land01 = new Land(cell01);
        cell01.setGameObject(land01);

        Cell cell02 = new Cell(0, 2);
        FixedGameObject land02 = new Land(cell02);
        cell02.setGameObject(land02);

        Cell cell03 = new Cell(0, 3);
        FixedGameObject land03 = new Land(cell03);
        cell03.setGameObject(land03);

        Cell cell04 = new Cell(0, 4);
        Monster monster04 = new Monster(cell04);
        cell04.setGameObject(monster04);

        Cell[][] cells = new Cell[][]{{cell00, cell01, cell02, cell03, cell04}};
        Monster[] monsters = new Monster[]{monster00, monster04};

        Field field = new Field(cells, monsters);

        field.start();
    }
}