package ru.job4j.iterator;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса class Converter.
 */
public class ConverterTest {
    /**
     * Тетс для метода next(), перебор двух итераторов.
     */
    @Test
    public void whenItHasTwoInnerIt() {
        Iterator<Iterator<Integer>> it = Arrays.asList(
                Collections.singletonList(1).iterator(),
                Collections.singletonList(2).iterator()
        ).iterator();
        Iterator<Integer> convert = new Converter().convert(it);
        convert.next();
        int result = convert.next();
        assertThat(result, is(2));
    }

    /**
     * Тест для метода hasNext(), должен вернуть false, если дошли до конца.
     */
    @Test
    public void whenItHasTwoThenMoveThreeTimesIsFalse() {
        Iterator<Iterator<Integer>> it = Arrays.asList(
                Collections.singletonList(1).iterator(),
                Collections.singletonList(2).iterator()
        ).iterator();
        Iterator<Integer> convert = new Converter().convert(it);
        convert.next();
        convert.next();
        convert.hasNext();
        boolean result = convert.hasNext();
        assertThat(result, is(false));
    }
}