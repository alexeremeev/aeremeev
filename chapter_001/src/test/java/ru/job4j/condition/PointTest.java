package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Тест для класса Point.
 *
 * @author Alexander Eremeev (mailto:eremeev@gmail.com)
 * @version $Id$
 * @since 26.08.2017
 */

public class PointTest {
    /**
     * Тест. Точка (0, 5) принадлежит функции y(x) = -10 * x + 5.
     */
    @Test
    public void whenPointOnLineThenTrue() {
        //create of new point.
        Point a = new Point(0, 5);
        // execute method - is and get result;
        boolean rsl = a.is(-10, 5);
        // assert result by excepted value.
        assertThat(rsl, is(true));
    }
}
