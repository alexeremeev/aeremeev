package ru.job4j.professions;

/**
 * Проект.
 */

public class Project {
    /**
     * Назнание проекта.
     */
    private String name;

    /**
     * Коструктор.
     * @param name название.
     */
    public Project(String name) {
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
