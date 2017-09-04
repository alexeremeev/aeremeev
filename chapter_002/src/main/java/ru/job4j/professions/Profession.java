package ru.job4j.professions;

/**
 * Материнский класс профессия.
 */
public class Profession {
    /**
     * Имя, специальность.
     */
    public String name, specialist;
    /**
     * Возраст.
     */
    public int age;

    /**
     * Геттер имени.
     * @return имя.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Геттер возраста.
     * @return возраст.
     */
    public int getAge() {
        return this.age;
    }

}
