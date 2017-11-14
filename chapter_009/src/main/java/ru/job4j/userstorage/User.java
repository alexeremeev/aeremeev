package ru.job4j.userstorage;

import java.sql.Timestamp;

/**
 * class User - модель пользователя.
 */
public class User {
    /**
     * Имя пользователя.
     */
    private String name;
    /**
     * Логин пользователя.
     */
    private String login;
    /**
     * E-mail пользователя.
     */
    private String email;
    /**
     * Дата создания пользователя.
     */
    private long createDate;

    /**
     * Конструктор.
     * @param name имя пользователя.
     * @param login логин пользователя.
     * @param email e-mail пользователя.
     * @param createDate дата создания пользователя.
     */
    public User(String name, String login, String email, long createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    /**
     * Геттер имени.
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * Геттер логина.
     * @return логин.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Геттер e-mail.
     * @return e-mail.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Геттер даты создания.
     * @return дата создания.
     */
    public long getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return String.format("Name: %s | Login: %s | E-mail: %s | Created: %s",
                this.getName(), this.getLogin(), this.getEmail(), new Timestamp(this.getCreateDate()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (getCreateDate() != user.getCreateDate()) {
            return false;
        }
        if (!getName().equals(user.getName())) {
            return false;
        }
        if (!getLogin().equals(user.getLogin())) {
            return false;
        }
        return getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + (int) (getCreateDate() ^ (getCreateDate() >>> 32));
        return result;
    }
}
