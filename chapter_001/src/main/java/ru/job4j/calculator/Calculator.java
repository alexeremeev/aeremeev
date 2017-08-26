package ru.job4j.calculator;

/**
 * Class Класс - калькулятор с базовыми операциями.
 * @author aeremeev
 * @since 26.08.2017
 * @version 1
 */

public class Calculator {
    /**
     * Я не знаю, почему checkstyle требует здесь JavaDoc.
     */
    private double result;

    /**
     * Сложение.
     * @param first первый аргумент.
     * @param second второй аргумент.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Вычитание.
     * @param first первый аргумент.
     * @param second второй аргумент.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Деление.
     * @param first первый аргумент.
     * @param second второй аргумент.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Умножение.
     * @param first первый аргумент.
     * @param second второй аргумент.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * Геттер резульата операции.
     * @return result результат
     */
    public double getResult() {
        return this.result;
    }
}
