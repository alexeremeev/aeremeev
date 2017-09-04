package ru.job4j.professions;

/**
 * Оценка.
 */
public class Mark {
    /**
     * Оценка прописью.
     */
    private String name;

    /**
     * Конструктор.
     * @param name оценка прописью.
     */
    public Mark(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}