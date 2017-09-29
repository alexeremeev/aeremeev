package ru.job4j.map;

import java.util.Calendar;

/**
 * class User модель пользователя.
 */
public class User {
    /**
     * Имя.
     */
    private String name;
    /**
     * Количество детей.
     */
    private int children;
    /**
     * Дата рождения.
     */
    private Calendar birthday;

    /**
     * Конструктор.
     * @param name имя.
     * @param children количество детей.
     * @param day день рождения.
     * @param month месяц рождения.
     * @param year год рождения.
     */
    public User(String name, int children, int day, int month, int year) {
        this.name = name;
        this.children = children;
        this.birthday = getCalendar(day, month, year);
    }

    /**
     * Возвращает Calendar с заполненной датой рождения.
     * @param day день.
     * @param month месяц.
     * @param year год.
     * @return Calendar с заполненной датой рождения.
     */
    public Calendar getCalendar(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal;
    }
}
