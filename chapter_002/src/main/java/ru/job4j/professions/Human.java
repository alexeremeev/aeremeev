package ru.job4j.professions;

/**
 * Человек.
 */
public class Human {
    /**
     * Иия.
     */
   private String name;

    /**
     * Конструктор.
     * @param name имя.
     */
    public Human(String name) {
        this.name = name;
    }

    /**
     * Геттер имени.
     * @return имя.
     */
    public String getName() {
        return this.name;
    }
}

