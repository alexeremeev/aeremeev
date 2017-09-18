package ru.job4j.control;

/**
 * class User - клиент банка.
 */
public class User {
    /**
     * Имя.
     */
    private String name;
    /**
     * Номер паспорта.
     */
    private int passport;

    /**
     * Конструктор.
     * @param name имя клиента.
     * @param passport номер паспорта.
     */
    public User(String name, int passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Equals.
     * @param o объект.
     * @return true, если совпадают имя и номер паспорта.
     */
    //CHECKSTYLE.OFF
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (this.passport != user.passport) return false;
        return this.name != null ? this.name.equals(user.name) : user.name == null;
    }
    //CHECKSTYLE.ON

    /**
     * HashCode.
     * @return номер паспорта.
     */
    @Override
    public int hashCode() {
        return this.passport;
    }
}
