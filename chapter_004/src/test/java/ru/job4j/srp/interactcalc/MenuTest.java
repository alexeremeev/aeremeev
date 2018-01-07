package ru.job4j.srp.interactcalc;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class Menu tests.
 * @author aeremeev
 * @since 07.01.2018
 * @version 1
 */
public class MenuTest {
    /**
     * Addition.
     */
    @Test
    public void whenAddTwoNumbersWhenGetCorrectResult() {
        Calculator calculator = new Calculator();
        String[] inputs = new String[] {"+", "2", "4.5", "e"};
        StubInput input = new StubInput(inputs);
        Menu menu = new Menu(input, calculator);
        menu.fillMenu();
        new InteractCalc(menu, input).init();

        double expected = 6.5d;

        assertThat(menu.getPrintedResult(), is(expected));
    }

    /**
     * Subtraction.
     */
    @Test
    public void whenSubtractTwoNumbersWhenGetCorrectResult() {
        Calculator calculator = new Calculator();
        String[] inputs = new String[] {"-", "2", "4.5", "e"};
        StubInput input = new StubInput(inputs);
        Menu menu = new Menu(input, calculator);
        menu.fillMenu();
        new InteractCalc(menu, input).init();

        double expected = -2.5d;

        assertThat(menu.getPrintedResult(), is(expected));
    }

    /**
     * Multiplication.
     */
    @Test
    public void whenMultiplyTwoNumbersWhenGetCorrectResult() {
        Calculator calculator = new Calculator();
        String[] inputs = new String[] {"*", "2", "4.5", "e"};
        StubInput input = new StubInput(inputs);
        Menu menu = new Menu(input, calculator);
        menu.fillMenu();
        new InteractCalc(menu, input).init();

        double expected = 9d;

        assertThat(menu.getPrintedResult(), is(expected));
    }

    /**
     * Division.
     */
    @Test
    public void whenDivideTwoNumbersWhenGetCorrectResult() {
        Calculator calculator = new Calculator();
        String[] inputs = new String[] {"/", "4.5", "2", "e"};
        StubInput input = new StubInput(inputs);
        Menu menu = new Menu(input, calculator);
        menu.fillMenu();
        new InteractCalc(menu, input).init();

        double expected = 2.25d;

        assertThat(menu.getPrintedResult(), is(expected));
    }

    /**
     * Repeat last operation.
     */
    @Test
    public void whenUseRepeatOperationWhenGetCorrectResult() {
        Calculator calculator = new Calculator();
        String[] inputs = new String[] {"*", "4.5", "2", "=", "=", "e"};
        StubInput input = new StubInput(inputs);
        Menu menu = new Menu(input, calculator);
        menu.fillMenu();
        new InteractCalc(menu, input).init();

        double expected = 36d;

        assertThat(menu.getPrintedResult(), is(expected));
    }

    /**
     * Clear.
     */
    @Test
    public void whenUseClearThenResultIsZero() {
        Calculator calculator = new Calculator();
        String[] inputs = new String[] {"*", "4.5", "2", "c", "e"};
        StubInput input = new StubInput(inputs);
        Menu menu = new Menu(input, calculator);
        menu.fillMenu();
        new InteractCalc(menu, input).init();

        double expected = 0;

        assertThat(menu.getPrintedResult(), is(expected));
    }

}