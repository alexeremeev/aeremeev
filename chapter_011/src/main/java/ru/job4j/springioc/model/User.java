package ru.job4j.springioc.model;

/**
 * User model.
 * @author aeremeev.
 * @version 1
 * @since 15.02.2018
 */
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
