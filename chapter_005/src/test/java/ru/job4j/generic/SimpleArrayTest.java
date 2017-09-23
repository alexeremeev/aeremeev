package ru.job4j.generic;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты класса SimpleArray с различными дженериками.
 */
public class SimpleArrayTest {
    /**
     * Тест методов add() и get().
     */
    @Test
    public void whenAddStringToSimpleArrayThenGetReturnsString() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("Test String");
        String expected = "Test String";

        assertThat(simpleArray.get(0), is(expected));
    }

    /**
     * Тест метода update().
     */
    @Test
    public void whenUpdateIntegeInSimpleArrayThenGetReturnsUpdatedInteger() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(5);
        simpleArray.add(100);
        simpleArray.update(0, 300);
        Integer expected = 300;

        assertThat(simpleArray.get(0), is(expected));
    }

    /**
     * Тест метода remove().
     */
    @Test
    public void whenRemoveThirdCharacterFromSimpleArrayWhenGetReturnsFourth() {
        SimpleArray<Character> simpleArray = new SimpleArray<>(5);
        simpleArray.add('A');
        simpleArray.add('B');
        simpleArray.add('C');
        simpleArray.add('D');
        simpleArray.add('E');

        simpleArray.remove(2);
        Character expected = 'D';

        assertThat(simpleArray.get(2), is(expected));

    }

}
