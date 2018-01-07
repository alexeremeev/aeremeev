package ru.job4j.srp.interactcalc;

/**
 * Class Calculator - basic calculator.
 * @author aeremeev
 * @since 06.01.2018
 * @version 1
 */
public class Calculator {
    /**
     * Operation result.
     */
    private double result;
    /**
     * Add.
     * @param first first arg.
     * @param second second arg.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Subtract.
     * @param first first arg.
     * @param second second arg.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Divide.
     * @param first first arg.
     * @param second second arg.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Multiply.
     * @param first first arg.
     * @param second second arg.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * Result getter.
     * @return result.
     */
    public double getResult() {
        return this.result;
    }
}
