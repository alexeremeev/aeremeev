package ru.job4j.professions;

/**
 * Класс инженер.
 */
public class Engineer extends Profession {
    /**
     * Квалификация, навык.
     */
    private String qualification, skill;
    /**
     * Конструктор.
     * @param name имя.
     * @param age возраст.
     */
    public Engineer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Разработка проекта.
     * @param task задание.
     * @return проект.
     */
    public Project develop(Task task) {
        return new Project("Инженер " + this.getName() +  " разрабатывает проект по " + task.getName());
    }
    /**
     * Анализ проекта.
     * @param project проект.
     * @return решение.
     */
    public Decision analyze(Project project) {
        return new Decision("Инженер " + this.getName() +  " анализирует проект по " + project.getName());
    }

    /**
     * Строительство.
     * @param project проект.
     * @return здание.
     */
    public Building build(Project project) {
        return new Building("Инженер " + this.getName() +  " строит " + project.getName());
    }

}
