package ru.job4j.ocp.scientificcalc;

import org.junit.Test;
import ru.job4j.srp.interactcalc.Calculator;
import ru.job4j.srp.interactcalc.Menu;
import ru.job4j.srp.interactcalc.StubInput;

import static org.junit.Assert.*;
/**
 * Class ScientificMenu tests.
 * @author aeremeev
 * @since 07.01.2018
 * @version 1
 */
public class ScientificMenuTest {
    /**
     * Sine.
     */
    @Test
    public void whenUseSineOperationWhenGetCorrectResult() {
        Calculator calculator = new Calculator();
        String[] inputs = new String[] {"sin", "30", "e"};
        StubInput input = new StubInput(inputs);
        Menu menu = new ScientificMenu(input, calculator);
        menu.fillMenu();
        new ScientificCalc(menu, input).init();

        double expected = 0.5d;

        assertEquals(menu.getPrintedResult(), expected, 0.000001);
    }

    /**
     * Cosine.
     */
    @Test
    public void whenUseCosineOperationWhenGetCorrectResult() {
        Calculator calculator = new Calculator();
        String[] inputs = new String[] {"cos", "60", "e"};
        StubInput input = new StubInput(inputs);
        Menu menu = new ScientificMenu(input, calculator);
        menu.fillMenu();
        new ScientificCalc(menu, input).init();

        double expected = 0.5d;

        assertEquals(menu.getPrintedResult(), expected, 0.000001);
    }

    /**
     * Tangent.
     */
    @Test
    public void whenUseTangentOperationWhenGetCorrectResult() {
        Calculator calculator = new Calculator();
        String[] inputs = new String[] {"tan", "45", "e"};
        StubInput input = new StubInput(inputs);
        Menu menu = new ScientificMenu(input, calculator);
        menu.fillMenu();
        new ScientificCalc(menu, input).init();

        double expected = 1d;

        assertEquals(menu.getPrintedResult(), expected, 0.000001);
    }

}