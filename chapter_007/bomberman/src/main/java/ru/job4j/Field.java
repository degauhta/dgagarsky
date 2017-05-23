package ru.job4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Field class.
 *
 * @author Denis
 * @since 12.05.2017
 */
class Field {
    /**
     * Cells matrix.
     */
    private Cell[][] cells;

    /**
     * Monster array.
     */
    private Monster[] monsters;

    /**
     * Field constructor.
     *
     * @param rowsNumber     number of rows
     * @param columnsNumber  number of columns
     * @param enableWalls    wall flag
     * @param monstersNumber number of monsters
     */
    Field(int rowsNumber, int columnsNumber, boolean enableWalls, int monstersNumber) {
        this.monsters = new Monster[monstersNumber];
        this.cells = new Cell[rowsNumber][columnsNumber];
        Cell newCell;
        FixedGameObject fixedGameObject;
        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < columnsNumber; j++) {
                newCell = new Cell(i, j);
                this.cells[i][j] = newCell;
                if (enableWalls & rowsNumber >= 3 & i % 2 == 1 & j % 2 == 1) {
                    fixedGameObject = new Wall(newCell);
                } else {
                    fixedGameObject = new Land(newCell);
                }
                this.cells[i][j].setGameObject(fixedGameObject);
            }
        }
    }

    /**
     * Default constructor.
     *
     * @param cells    matrix of cells
     * @param monsters array of monsters
     */
    Field(Cell[][] cells, Monster[] monsters) {
        this.cells = cells;
        this.monsters = monsters;
    }

    /**
     * Start.
     */
    void start() {
        for (int i = 0; i < this.monsters.length; i++) {
            this.monsters[i].setField(this);
            this.monsters[i].setName(i);
            Thread th1 = new Thread(this.monsters[i]);
            th1.start();
        }

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                print();
                for (Monster m : monsters) {
                    m.setTimeToMove();
                }
            }
        }, 0, 1, TimeUnit.SECONDS);

        while (true) {
            if (this.monsters.length == 0) {
                break;
            }
        }
    }

    /**
     * Get matrix of cells.
     *
     * @return cells
     */
    Cell[][] getCells() {
        return this.cells;
    }

    /**
     * Get cell.
     *
     * @param row    row
     * @param column column
     * @return cell
     */
    Cell getCell(int row, int column) {
        return this.cells[row][column];
    }

    /**
     * Move object.
     *
     * @param activeGameObject object to move
     * @param newPosition      new position of object
     * @return true if movement successfully
     */
    boolean move(ActiveGameObject activeGameObject, Cell newPosition) {
        boolean result = true;
        Cell position = activeGameObject.getPosition();
        Land land = new Land(position);

        result = newPosition.setGameObject(activeGameObject);
        if (result) {
            position.setGameObject(land);
            activeGameObject.setPosition(newPosition);
        }
        return result;
    }

    /**
     * Print matrix of cells.
     */
    private void print() {
        for (Cell[] cells1 : this.cells) {
            for (Cell cell : cells1) {
                System.out.print(cell.toString());
            }
            System.out.println();
        }
    }
}
