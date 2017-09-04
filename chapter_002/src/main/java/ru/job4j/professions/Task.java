package ru.job4j.professions;

/**
 * Задача.
 */
public class Task {
    /**
     * Задача.
     */
    private String name;

    /**
     * Коструктор.
     * @param name название задачи.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Геттер названия.
     * @return название.
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}

