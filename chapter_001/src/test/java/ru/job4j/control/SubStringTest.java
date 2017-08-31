package ru.job4j.control;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест для класса SubString.
 */
public class SubStringTest {
    /**
     * Ищем "роф" в "Синхрофазотроне".
     */
    @Test
    public void originContainsSub() {
        SubString subString = new SubString();
        String full = "Синхрофазотрон";
        String sub = "роф";
        boolean expeted = true;
        assertThat(subString.contains(full, sub), is(expeted));
    }
}
