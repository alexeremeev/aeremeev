package ru.job4j.professions;

/**
 * Здоровье.
 */

public class Health {
    /**
     * Показатель здоровья.
     */
    private String name;

    /**
     * Конструктор здоровья.
     * @param name показатель здоровье.
     */
    public Health(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}