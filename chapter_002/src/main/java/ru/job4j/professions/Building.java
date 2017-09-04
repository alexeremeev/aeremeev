package ru.job4j.professions;

/**
 * Класс Строение.
 */
public class Building {
    /**
     * Название строения.
     */
    private String name;

    /**
     * Конструктор строения.
     * @param name название строения.
     */
    public Building(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}