package ru.job4j.control;
/**
 * class Anagram - проверка одного слова на анаграмму другого.
 * Используем массив целочисленных чисел, где каждый индекс - это (int)char, а значение - количество этих букв в слове.
 * Во втором цикле мы отнимает по одной соответствующие буквы из массива и проверяем, чтобы итог не выходил за 0.
 */
public class Anagram {
    //CHECKSTYLE.OFF
    private final static int ALPHABET = 1104;
    //CHECKSTYLE.ON
    /**
     * Метод сравнивает две строки на анаграмму.
     * @param original первая строка.
     * @param shuffle вторая строка.
     * @return true, если найдена анаграмма.
     */
    public boolean reshuffle(String original, String shuffle) {
        boolean confirm = false;
        if (original.length() == shuffle.length()) {
            confirm = true;
            int[] chars = new int[ALPHABET];
            for (char c : original.toCharArray()) {
                chars[c]++;
            }
            for (int index = 0; index != shuffle.length(); index++) {
                int c = (int) shuffle.charAt(index);
                if (--chars[c] < 0) {
                    confirm = false;
                    break;
                }
            }
        }
        return confirm;
    }
}
