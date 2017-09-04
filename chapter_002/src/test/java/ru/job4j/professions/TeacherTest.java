package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестируем образование.
 */
public class TeacherTest {
    /**
     * Тест для преподавания.
     */
    @Test
    public void tryTeacherToTeach() {
        Teacher teacher = new Teacher("Олег", 28);
        String expected = "Учитель Олег учит Сергей";
        String actual = teacher.teach(new Human("Сергей")).toString();
        assertThat(actual, is(expected));
    }
    /**
     * Тест для проверки.
     */
    @Test
    public void tryTeacherToCheck() {
        Teacher teacher = new Teacher("Олег", 28);
        String expected = "Учитель Олег проверяет работу по математика";
        String actual = teacher.check(new Task("математика")).toString();
        assertThat(actual, is(expected));
    }
    /**
     * Тест для воспитания.
     */
    @Test
    public void tryTeacherToNurture() {
        Teacher teacher = new Teacher("Олег", 28);
        String expected = "Учитель Олег воспитывает Сергей";
        String actual = teacher.nurture(new Human("Сергей")).toString();
        assertThat(actual, is(expected));
    }
}
