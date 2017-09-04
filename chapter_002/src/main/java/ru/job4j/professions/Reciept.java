package ru.job4j.professions;

/**
 * Рецепт.
 */
public class Reciept {
    /**
     * Рецепт на что.
     */
    private String name;

    /**
     * Конструктор.
     * @param name назнание лекартства.
     */
    public Reciept(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}