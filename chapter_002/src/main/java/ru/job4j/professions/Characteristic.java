package ru.job4j.professions;

/**
 * Класс характеристика ученика.
 */
public class Characteristic {
    /**
     * Характеристика ученика.
     */
    private String name;

    /**
     * Конструктор характристика.
     * @param name характеристика.
     */
    public Characteristic(String name) {
        this.name = name;
    }

    /**
     * Возврат характеристики.
     * @return характеристика ученика.
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}