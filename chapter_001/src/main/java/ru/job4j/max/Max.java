package ru.job4j.max;

/**
 * Class Класс - выбор максимального из двух целых чисел.
 * @author aeremeev
 * @since 26.08.2017
 * @version 1.1
 */
public class Max {
    /**
     * Выбор максимального из двух целых чисел.
     * @param first первый агрумент.
     * @param second второй аргумент.
     * @return результат.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }
    /**
     * Выбор максимального из трех целых чисел.
     * @param first первый аргумент.
     * @param second второй аргумент.
     * @param third третий аргумент.
     * @return результат.
     */
    public int max(int first, int second, int third) {
        return max(first, max(second, third));
    }
}
