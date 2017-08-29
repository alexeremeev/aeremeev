package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест для класса Turn.
 */
public class TurnTest {
    /**
     * Переворот массива из четного числа ячеек.
     */
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        Turn turn = new Turn();
        int[] arrayExpeced = {4, 1, 6, 2};
        int[] arrayActual = {2, 6, 1, 4};
        arrayActual = turn.back(arrayActual);
        assertThat(arrayActual, is(arrayExpeced));
    }

    /**
     * Переворот массива из нечетного числа ячеек.
     */
    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        Turn turn = new Turn();
        int[] arrayExpeced = {5, 4, 3, 2, 1};
        int[] arrayActual = {1, 2, 3, 4, 5};
        arrayActual = turn.back(arrayActual);
        assertThat(arrayActual, is(arrayExpeced));
    }
}