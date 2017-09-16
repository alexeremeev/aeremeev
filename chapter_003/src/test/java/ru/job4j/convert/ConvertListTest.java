package ru.job4j.convert;
import org.junit.Test;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса ConvertList.
 */
public class ConvertListTest {
    /**
     * Тестирование конвертации массива с список.
     */
    @Test
    public void tryConvertArrayToList() {
        ConvertList convertList = new ConvertList();
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        ArrayList<Integer> expected = new ArrayList<>();
        for (int index = 0; index != 9; index++) {
            expected.add(index + 1);
        }
        assertThat(convertList.toList(array), is(expected));
    }
    /**
     * Тестирование конвертации списка в массив.
     */
    @Test
    public void tryConvertListToMatrix() {
        ConvertList convertList = new ConvertList();
        ArrayList<Integer> list = new ArrayList<>();
        for (int index = 0; index != 7; index++) {
            list.add(index + 1);
        }
        int[][] expected = {{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        assertThat(convertList.toArray(list, 3), is(expected));

    }


}
