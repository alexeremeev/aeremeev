package ru.job4j.professions;

/**
 * Учитель.
 */
public class Teacher extends Profession {
    /**
     * Ученая степень.
     */
   private String degree;
    /**
     * Конструктор.
     * @param name имя.
     * @param age возраст.
     */
    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Учить.
     * @param human человек.
     * @return знание.
     */
    public Knowledge teach(Human human) {
        return new Knowledge("Учитель " + this.getName() + " учит " + human.getName());
    }

    /**
     * Проверять.
     * @param task задание.
     * @return оценка.
     */
    public Mark check(Task task) {
        return new Mark("Учитель " + this.getName() + " проверяет работу по " + task.getName());
    }

    /**
     * Воспитывать.
     * @param human человек.
     * @return характеристика.
     */
    public Characteristic nurture(Human human) {
        return new Characteristic("Учитель " + this.getName() + " воспитывает " + human.getName());
    }


}
