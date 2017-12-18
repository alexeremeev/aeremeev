package ru.job4j.io.chat;

/**
 * class StubIO - ввод заглушками.
 */
public class StubIO implements InputOutput {
    /**
     * Массив сообщений пользователя.
     */
    private String[] inputs;
    /**
     * Индекс в массиве.
     */
    private int index = 0;
    /**
     * Вывод в StringBuilder.
     */
    private StringBuilder output = new StringBuilder();
    /**
     * Строка разделитель.
     */
    private final String separator = System.getProperty("line.separator");

    /**
     * Конструктор.
     * @param inputs массив сообщений пользователя.
     */
    public StubIO(String[] inputs) {
        this.inputs = inputs;
    }

    /**
     * Считывает строку из массива.
     * @return строка из массива.
     */
    @Override
    public String read() {
        return inputs[index++];
    }

    /**
     * Запись сообщения в StringBuilder.
     * @param message сообщение.
     */
    @Override
    public void println(String message) {
        output.append(String.format("%s%s", message, separator));
    }

    /**
     * Запись сообщения в StringBuilder.
     * @param message сообщение.
     */
    @Override
    public void print(String message) {
        output.append(String.format("%s%s", message, separator));
    }

    /**
     * Получить вывод в виде строки.
     * @return вывод в виде строки.
     */
    public String getOutput() {
        return output.toString();
    }
}
