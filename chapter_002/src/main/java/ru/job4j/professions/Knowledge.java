package ru.job4j.professions;

/**
 * Знание.
 */
public class Knowledge {
    /**
     * Знание.
     */
    private String know;

    /**
     * Конструктор.
     * @param know знание.
     */
    public Knowledge(String know) {
        this.know = know;
    }

    @Override
    public String toString() {
        return know;
    }
}
