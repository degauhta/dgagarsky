package ru.job4j;

/**
 * CalculatorTrigonometric class.
 *
 * @author Denis
 * @since 11.02.2017
 */
public class CalculatorTrigonometric extends Calculator {
    /***
     * Result.
     */
    private double result;

    /**
     * Summation of numbers, write in field result.
     *
     * @param first  the first number.
     * @param second the second number
     */
    @Override
    public void add(double first, double second) {
        super.add(first, second);
        setResult();
    }

    /**
     * Subtraction  of numbers, write in field result.
     *
     * @param first  the first number.
     * @param second the second number
     */
    @Override
    public void substruct(double first, double second) {
        super.substruct(first, second);
        setResult();
    }

    /**
     * Multiplication of numbers, write in field result.
     *
     * @param first  the first number.
     * @param second the second number
     */
    @Override
    public void multiple(double first, double second) {
        super.multiple(first, second);
        setResult();
    }

    /**
     * Division of numbers, write in field result.
     *
     * @param first  the first number.
     * @param second the second number.
     */
    @Override
    public void div(double first, double second) {
        super.div(first, second);
        setResult();
    }

    /**
     * Sines of degree.
     *
     * @param degree degree.
     */
    public void sin(double degree) {
        this.result = Math.sin(Math.toRadians(degree));
    }

    /**
     * Cosines of degree.
     *
     * @param degree degree.
     */
    public void cos(double degree) {
        this.result = Math.cos(Math.toRadians(degree));
    }

    /**
     * Set current result.
     */
    private void setResult() {
        this.result = super.getResult();
    }

    /**
     * Get field result.
     *
     * @return field result
     */
    @Override
    public double getResult() {
        return this.result;
    }
}