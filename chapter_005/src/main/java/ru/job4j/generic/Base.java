package ru.job4j.generic;

/**
 * abstract class Base - базовый абстрактный класс элемента в хранилище.
 */

public abstract class Base {
    /**
     * Id элемента.
     */
    private String id;

    /**
     * Конструктор.
     * @param id Id.
     */
    public Base(String id) {
        this.id = id;
    }

    /**
     * Геттер Id.
     * @return ID.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Сеттер Id.
     * @param id Id.
     */
    public void setId(String id) {
        this.id = id;
    }

}
