package ru.job4j.tracker.input;

/**
 * Интерфейс Input для ввода данных.
 */
public interface Input {
    /**
     * Запрос пользовательского ввода.
     * @param question описание запрашиваемого значения.
     * @return пользовательский ввод.
     */
    String ask(String question);

    /**
     * Запрос пользовательского ввода с проверкой номера меню.
     * @param question описание запрашиваемого значения.
     * @param range массив номеров пунктов меню.
     * @return пользовательский ввод.
     */
    int ask(String question, int[] range);
}
