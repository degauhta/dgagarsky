package ru.job4j;

/**
 * InteractCalc class.
 *
 * @author Denis
 * @since 06.02.2017
 */
public class InteractCalc {
    /**
     * Input.
     */
    private final InputCalc input;

    /**
     * Menu Calculator.
     */
    private final MenuCalculator menuCalculator;

    /**
     * Menu range.
     */
    private int[] menuRange;

    /**
     * Menu size.
     */
    static final int MENU_SIZE = 4;

    /**
     * Constructor.
     * @param input input.
     * @param menuCalculator menuCalculator.
     * @param menuRange menuRange.
     */
    InteractCalc(final InputCalc input, final MenuCalculator menuCalculator, final int[] menuRange) {
        this.input = input;
        this.menuCalculator = menuCalculator;
        this.menuRange = menuRange;
    }

    /**
     * Initialization.
     */
    void init() {
        do {
            this.menuCalculator.showMenu();
            this.menuCalculator.select(this.input.ask("Select: ", this.menuRange));
        } while (!"y".equals(this.input.ask("Exit? (y):")));
    }

    /**
     * Main method.
     * @param args args.
     */
    public static void main(String[] args) {
        InputCalc input = new ValidateInputCalc();
        Calculator calculator = new Calculator();
        MenuCalculator menuCalculator = new MenuCalculator(input, calculator, MENU_SIZE);
        int[] menuRange = menuCalculator.fillAction();
        new InteractCalc(input, menuCalculator, menuRange).init();
    }
}