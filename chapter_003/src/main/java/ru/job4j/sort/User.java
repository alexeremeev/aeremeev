package ru.job4j.sort;

/**
 * class User - пользователь.
 */
public class User implements Comparable<User> {
    /**
     * Имя.
     */
    private String name;
    /**
     * Возраст.
     */
    private Integer age;

    /**
     * Конструктор.
     * @param name имя.
     * @param age возраст.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Переопределенный compareTo.
     * @param o User.
     * @return User с меньшим возрастом.
     */
    @Override
    public int compareTo(User o) {
        return this.age.compareTo(o.age);
    }

    /**
     * Переопределенный toString.
     * @return строка с данными объекта User.
     */
    @Override
    public String toString() {
        return "User{" + "name='"
                + name + '\''
                + ", age="
                + age + '}';
    }
}
