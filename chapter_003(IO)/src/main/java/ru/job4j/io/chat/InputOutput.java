package ru.job4j.io.chat;

/**
 * interface InputOutput - интерфейс ввода/вывода для чата.
 */
public interface InputOutput {
    /**
     * Считывает введеную строка.
     * @return строка.
     */
    String read();

    /**
     * Вывод сообщения с переходом на новую строку.
     * @param message сообщение.
     */
    void println(String message);

    /**
     * Вывод сообщения.
     * @param message сообщение.
     */
    void print(String message);
}
