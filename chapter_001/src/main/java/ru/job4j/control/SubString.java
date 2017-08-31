package ru.job4j.control;
/**
 * Class SubString - поиск подстроки в строке с использованием символьных массивов.
 * @author aeremeev.
 * @since 30.08.2017.
 * @version 1.
 */

public class SubString {
    /**
     * Метод поиска подстроки в строке с использованием символьных массивов.
     * @param origin исходная строка.
     * @param sub подстрока.
     * @return true, если найдено вхождение подстроки в строку
     */
    boolean contains(String origin, String sub) {
        char[] arrayOrigin = origin.toCharArray();
        char[] arraySub = sub.toCharArray();
        boolean originContainsSub = false;
        for (int i = 0; i < arrayOrigin.length; i++) {
            if (arrayOrigin[i] == arraySub[0]) {
                originContainsSub = true;
                for (int j = 1; j < arraySub.length; j++) {
                    if (arraySub[j] != arrayOrigin[i + j]) {
                        originContainsSub = false;
                        break;
                    }
                }
            }
            if (originContainsSub) {
                break;
            }
        } return originContainsSub;
    }
}
