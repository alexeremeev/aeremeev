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

    //CHECKSTYLE.OFF
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + (birthday != null ? birthday.get(Calendar.DATE)
                + birthday.get(Calendar.MONTH) + birthday.get(Calendar.YEAR) : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (children != user.children) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return birthday != null ? birthday.get(Calendar.DATE) == user.birthday.get(Calendar.DATE) &&
                birthday.get(Calendar.MONTH) == user.birthday.get(Calendar.MONTH) &&
                birthday.get(Calendar.YEAR) == user.birthday.get(Calendar.YEAR): user.birthday == null;
    }
    //CHECKSTYLE.ON
}
