package ru.job4j.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionResolver {

    public List<Double> diapason(int start, int end, Function<Double, Double> function) {
        List<Double> result = new ArrayList<>();
        for (double i = start; i != end; i++) {
            result.add(function.apply(i));
        }
        return result;
    }
}
