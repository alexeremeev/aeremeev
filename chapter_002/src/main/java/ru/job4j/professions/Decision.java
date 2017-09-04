package ru.job4j.professions;

/**
 * Класс решение.
 */
public class Decision {
    /**
     * Решение о строительстве.
     */
    private String name;

    /**
     * Конструктор решения.
     * @param name решение.
     */
    public Decision(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}