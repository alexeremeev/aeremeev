package ru.job4j.tracker;

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
}
