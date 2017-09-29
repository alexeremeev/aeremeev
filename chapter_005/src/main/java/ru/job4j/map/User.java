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
        this.birthday = Calendar.getInstance();
        this.birthday.set(year, month, day);
    }


    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + (birthday != null ? birthday.get(Calendar.DATE) + birthday.get(Calendar.MONTH) + birthday.get(Calendar.YEAR) : 0);
        return result;
    }

}
