package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Тест для класса Max.
 *
 * @author Alexander Eremeev (mailto:eremeev@gmail.com)
 * @version $Id$
 * @since 26.08.2017
 */
public class MaxTest {
    /**
     * Тест 2 > 1 = true.
     */
    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }
}
