package ru.job4j.iterator;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса PrimeIterator.
 */
public class PrimeIteratorTest {

    /**
     * Тест метода next, из массива {3, 4, 5, 6, 7} должен выбрать и вернуть {3, 5, 7}.
     */
    @Test
    public void whenMoveThroughArrayWithThreePrimeNumbersThenReturnThreePrimeNumbers() {
        PrimeIterator primeIterator = new PrimeIterator(new int[] {3, 4, 5, 6, 7});
        int[] array = new int[] {(Integer) primeIterator.next(), (Integer) primeIterator.next(), (Integer) primeIterator.next()};
        int[] expected = new int[] {3, 5, 7};
        assertThat(array, is(expected));
    }

    /**
     * Тест метода hasNext, возращает false, после выбора трех простых чисел из {3, 5, 7, 4, 6}.
     */
    @Test
    public void whenMoveThroughArrayThreeTimesThenHasNextIsFalse() {
        PrimeIterator primeIterator = new PrimeIterator(new int[] {3, 5, 7, 4, 6});
        primeIterator.next();
        primeIterator.next();
        primeIterator.next();
        primeIterator.hasNext();
        boolean result = primeIterator.hasNext();
        assertThat(result, is(false));
    }
}
