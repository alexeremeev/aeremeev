package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест на все операции для калькулятора.
 *
 * @author Alexander Eremeev (mailto:eremeev@gmail.com)
 * @version $Id$
 * @since 26.08.2017
 */
public class CalculatorTest {
    /**
     * Тест сложения 1+1=2.
     */
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }
    /**
     * Тест вычитания 10-5=5.
     */
    @Test
    public void whenSubtractTenMinusFiveThenFive() {
        Calculator calc = new Calculator();
        calc.subtract(10D, 5D);
        double result = calc.getResult();
        double expected = 5D;
        assertThat(result, is(expected));
    }
    /**
     * Тест деления 12/2=6.
     */
    @Test
    public void whenDivTwelveByTwoThenSix() {
        Calculator calc = new Calculator();
        calc.div(12D, 2D);
        double result = calc.getResult();
        double expected = 6D;
        assertThat(result, is(expected));
    }
    /**
     * Тест умножения 3*4=12.
     */
    @Test
    public void whenMultipleThreeByFourThenTwelve() {
        Calculator calc = new Calculator();
        calc.multiple(3D, 4D);
        double result = calc.getResult();
        double expected = 12D;
        assertThat(result, is(expected));
    }
}
