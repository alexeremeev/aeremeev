package ru.job4j.functions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FunctionResolverTest {

    @Test
    public void whenFunctionIsDoubleLinearThenGetExpectedList() {
        FunctionResolver resolver = new FunctionResolver();
        List<Double> expected = new ArrayList<>(Arrays.asList(0D, 2D, 4D, 6D, 8D));

        List<Double> result = resolver.diapason(0, 5, ((value) -> (value = 2 * value)));

        assertThat(result, is(expected));
    }

    @Test
    public void whenFunctionIsQuadraticThenGetExpectedList() {
        FunctionResolver resolver = new FunctionResolver();
        List<Double> expected = new ArrayList<>(Arrays.asList(0D, 1D, 4D, 9D, 16D));

        List<Double> result = resolver.diapason(0, 5, ((value) -> (Math.pow(value, 2))));

        assertThat(result, is(expected));
    }

    @Test
    public void whenFunctionIsLogarithmicThenGetExpectedList() {
        FunctionResolver resolver = new FunctionResolver();
        List<Double> expected = new ArrayList<>(Arrays.asList(0.6931471805599453, 0.6931471805599453,
                0.6931471805599453, 0.6931471805599453, 0.6931471805599453));

        List<Double> result = resolver.diapason(10, 15, ((value) -> (Math.log(2))));

        assertThat(result, is(expected));
    }

}