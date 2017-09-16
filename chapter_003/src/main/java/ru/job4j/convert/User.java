package ru.job4j.convert;

/**
 * class User - пользователь.
 */
public class User {
    /**
     * Id пользователя.
     */
    private int id;
    /**
     * Имя пользователя.
     */
    private String name;
    /**
     * Город пользователя.
     */
    private String city;

    /**
     * Конструктор.
     * @param id Id.
     * @param name имя.
     * @param city город.
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Геттер Id.
     * @return Id данного пользователя.
     */
    public int getId() {
        return this.id;
    }
}
