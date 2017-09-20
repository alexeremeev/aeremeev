package ru.job4j.iterator;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса EvenIterator.
 */
public class EvenIteratorTest {
    /**
     * Тест метода next(), при вызове в массиве {4, 1, 2, 1} 2 раза должен вернуть 2.
     */
    @Test
    public void whenMoveThroughArrayWithTwoEvenNumbersThenRecieveTwoEvenNumbers() {
        EvenIterator evenIterator = new EvenIterator(new int[] {4, 1, 2, 1});

        evenIterator.next();
        int result = (Integer) evenIterator.next();

        assertThat(result, is(2));
    }

    /**
     * Тест метода hasNext(), после вызова next() 2 раза должен вернуть false.
     */
    @Test
    public void whenMoveThroughArrayThreeTimesThenHasNextIsFalse() {
        EvenIterator evenIterator = new EvenIterator(new int[] {4, 1, 2, 1});

        evenIterator.next();
        evenIterator.next();
        evenIterator.hasNext();
        boolean result = evenIterator.hasNext();

        assertThat(result, is(false));
    }
}
