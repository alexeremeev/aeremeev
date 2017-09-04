package ru.job4j.professions;

/**
 * Класс болезни.
 */
public class Disease {
    /**
     * Название болезни.
     */
    private String name;

    /**
     * Конструктор болезни.
     * @param name болезнь.
     */
    public Disease(String name) {
        this.name = name;
    }

    /**
     * Геттер болезни.
     * @return болезнь.
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}

