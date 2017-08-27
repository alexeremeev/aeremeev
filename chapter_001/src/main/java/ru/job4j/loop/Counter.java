package ru.job4j.loop;
/**
 * Class Counter - подсчет четных целых чисел в заданном диапазоне.
 * @author aeremeev.
 * @since 27.08.2017.
 * @version 1.
 */

public class Counter {
    /**
     * Метод подсчета четных целых чисел.
     * @param start начало диапазона.
     * @param finish конец диапазона.
     * @return результат.
     */
    public int add(int start, int finish) {
        int result = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                result = result + i;
            }
        }
        return result;
    }
}
