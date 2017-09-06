package ru.job4j.tracker;

/**
 * Class StubInput - ввод данных с помощью заглушек.
 */
public class StubInput implements Input {
    /**
     * Массив пользовательского ввода.
     */
    private String[] answers;
    /**
     * Начальная позиция массива.
     */
    private int position = 0;

    /**
     * Конструктор массива.
     * @param answers последовательные ответы.
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Реализация метода ask интерфейса Input.
     * @param question описание запрашиваемого значения.
     * @return ответы из заглушки.
     */
    public String ask(String question) {
        return answers[position++];
    }
}
