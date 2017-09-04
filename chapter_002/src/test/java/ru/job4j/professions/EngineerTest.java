package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестируем инженерию.
 */
public class EngineerTest {
    /**
     * Тест для разработки.
     */
    @Test
    public void tryEngineerToDevelop() {
        Engineer engineer = new Engineer("Игорь", 32);
        String expected = "Инженер Игорь разрабатывает проект по мост";
        String actual = engineer.develop(new Task("мост")).toString();
        assertThat(actual, is(expected));
    }
    /**
     * Тест для анализа.
     */
    @Test
    public void tryEngineerToAnalyze() {
        Engineer engineer = new Engineer("Игорь", 32);
        String expected = "Инженер Игорь анализирует проект по мост";
        String actual = engineer.analyze(new Project("мост")).toString();
        assertThat(actual, is(expected));
    }
    /**
     * Тест для строительства.
     */
    @Test
    public void tryEngineerToBuild() {
        Engineer engineer = new Engineer("Игорь", 32);
        String expected = "Инженер Игорь строит мост";
        String actual = engineer.build(new Project("мост")).toString();
        assertThat(actual, is(expected));
    }
}