package ru.job4j.srp.interactcalc;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Calculator tests.
 *
 * @author Alexander Eremeev (mailto:eremeev@gmail.com)
 * @version 1
 * @since 06.01.2018
 */
public class CalculatorTest {
    /**
     * Addition test 1+1=2.
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
     * Subtraction test 10-5=5.
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
     * Division test 12/2=6.
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
     * Multiplication test 3*4=12.
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