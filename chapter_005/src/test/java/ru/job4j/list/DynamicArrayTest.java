package ru.job4j.list;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса DynamicArray.
 */
public class DynamicArrayTest {
    /**
     * Тест методов add() get().
     */
    @Test
    public void whenAddNewItemsInDynamicArrayThenRecieveLast() {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        dynamicArray.add(1);
        dynamicArray.add(2);
        dynamicArray.add(3);
        dynamicArray.add(4);

        assertThat(dynamicArray.get(3), is(4));
    }
}
