package ru.job4j;

/**
 * Monster class.
 *
 * @author Denis
 * @since 12.05.2017
 */
class Monster extends ActiveGameObject implements Runnable {
    /**
     * Alive flag.
     */
    private boolean alive = true;

    /**
     * Movement direction.
     */
    private String direction = "up";

    /**
     * Name.
     */
    private String name;

    /**
     * Default constructor.
     *
     * @param position cell
     */
    Monster(Cell position) {
        super(position, true);
    }

    /**
     * Set name.
     *
     * @param name name
     */
    void setName(int name) {
        this.name = String.format("M%s", name);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        getPosition().getLock().lock();
        this.direction = "left";
        while (alive) {
            while (getTimeToMove().get()) {
                int row = getPosition().getRow();
                int column = getPosition().getColumn();
                try {
                    if (!move(row, column)) {
                        changeDirection();
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    changeDirection();
                }
                getTimeToMove().set(false);
            }
        }
        System.out.println(alive);
    }

    /**
     * Move monster.
     *
     * @param row    current row number.
     * @param column current column number
     * @return true if movement successfully
     */
    private boolean move(int row, int column) {
        boolean result = false;
        if (this.direction.equals("up")) {
            result = getField().move(this, getField().getCell(row + 1, column));
        } else if (this.direction.equals("right")) {
            result = getField().move(this, getField().getCell(row, column + 1));
        } else if (this.direction.equals("down")) {
            result = getField().move(this, getField().getCell(row, column - 1));
        } else if (this.direction.equals("left")) {
            result = getField().move(this, getField().getCell(row - 1, column));
        }
        return result;
    }

    /**
     * Change direction of movement.
     */
    private void changeDirection() {
        if (this.direction.equals("up")) {
            this.direction = "right";
        } else if (this.direction.equals("right")) {
            this.direction = "down";
        } else if (this.direction.equals("down")) {
            this.direction = "left";
        } else if (this.direction.equals("left")) {
            this.direction = "up";
        }
    }
}
