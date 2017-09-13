package ru.job4j.control;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест для класса Anagram.
 */
public class AnagramTest {
    /**
     * Тестируем не самую удачную анаграмму синхрофазотрона.
     */
    @Test
    public void tryAnagramforWierdWord() {
        Anagram anagram = new Anagram();
        assertThat(anagram.reshuffle("синхрофазотрон", "хронизаторфонс"), is(true));
    }
}
