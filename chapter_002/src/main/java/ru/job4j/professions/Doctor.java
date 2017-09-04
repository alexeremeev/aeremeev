package ru.job4j.professions;

/**
 * Класс доктор.
 */
public class Doctor extends Profession {
    /**
     * Диплом.
     */
    private String diplom;

    /**
     * Конструктор.
     * @param name имя.
     * @param age возраст.
     */
    public Doctor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Диагностировать человека.
     * @param human человек.
     * @return болезнь.
     */
    public Disease diagnose(Human human) {
        return new Disease("Доктор " + this.getName() +  " диагностирует болезнь у " + human.getName());
    }

    /**
     * Лечить болезнь.
     * @param disease болезнь.
     * @return Здоровье.
     */
    public Health heal(Disease disease) {
        return new Health("Доктор " + this.getName() +  " лечит " + disease.getName());
    }

    /**
     * Выписывать лекартсва.
     * @param disease болезнь.
     * @return рецепт.
     */
    public Reciept getMedicine(Disease disease) {
        return new Reciept("Доктор " + this.getName() +  " выписывает лекарство от " + disease.getName());
    }

}
