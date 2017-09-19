package ru.job4j.iterator;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса MatrixIterator.
 */
public class MatrixIteratorTest {

    /**
     * Проверка метода next, из матрицы {{1, 2}, {3, 4}} должны получить массив {1, 2, 3, 4}.
     */
    @Test
    public void whenMoveThroughMatrixThenRecieveArray() {
        MatrixIterator matrixIterator = new MatrixIterator(new int[][] {{1, 2}, {3, 4}});

        int[] array = new int[] {(Integer) matrixIterator.next(), (Integer) matrixIterator.next(),
                (Integer) matrixIterator.next(), (Integer) matrixIterator.next()};

        int[] expected = new int[] {1, 2, 3, 4};

        assertThat(array, is(expected));
    }

    /**
     * Проверка метода hasNext.
     */
    @Test
    public void whenMoveThroughMatrixFiveTimesHasNextIsFalse() {
        MatrixIterator matrixIterator = new MatrixIterator(new int[][] {{1}, {2}});

        matrixIterator.next();
        matrixIterator.next();
        matrixIterator.hasNext();
        boolean result = matrixIterator.hasNext();

        assertThat(result, is(false));
    }

}
