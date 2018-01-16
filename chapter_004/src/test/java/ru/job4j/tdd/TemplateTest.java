package ru.job4j.tdd;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Template interface tests via SimpleGenerator.
 * @author aeremeev
 * @since 16.01.2018
 * @version 1
 */
public class TemplateTest {
    /**
     * Test of correct substitutes with different keys in string.
     */
    @Test
    public void whenStringHaveKeysThenGetCorrectSubstitute() {
        Template template = new SimpleGenerator();
        Map<String, String> substitutes = new HashMap<>();
        substitutes.put("name", "Alex");
        substitutes.put("who", "you");
        String original = "Hello ${name}, how are ${who}?";
        String expected = "Hello Alex, how are you?";

        String actual = template.generate(original, substitutes);

        assertThat(actual, is(expected));
    }

    /**
     * Test of correct substitutes with one multiple key in string.
     */
    @Test
    public void whenStringHaveManyEqualKeysThenGetCorrectSubstitute() {
        Template template = new SimpleGenerator();
        Map<String, String> substitutes = new HashMap<>();
        substitutes.put("sos", "Aaa");
        String original = "Help, ${sos}, ${sos}, ${sos}!!!";
        String expected = "Help, Aaa, Aaa, Aaa!!!";

        String actual = template.generate(original, substitutes);

        assertThat(actual, is(expected));
    }

    /**
     * Test with null template.
     * @throws TemplateException TemplateException must be thrown.
     */
    @Test(expected = TemplateException.class)
    public void whenTemplateStringIsNullThenExceptionThrown() throws TemplateException {
        Template template = new SimpleGenerator();
        Map<String, String> substitutes = new HashMap<>();
        substitutes.put("sos", "Aaa");
        String expected = "Help, Aaa, Aaa, Aaa!!!";

        String actual = template.generate(null, substitutes);

        assertThat(actual, is(expected));
    }

    /**
     * Test with spare keys in template string.
     * @throws TemplateException TemplateException must be thrown.
     */
    @Test(expected = TemplateException.class)
    public void whenStringHaveIncorrectKeyThenExceptionThrown() throws TemplateException {
        Template template = new SimpleGenerator();
        Map<String, String> substitutes = new HashMap<>();
        substitutes.put("name", "Alex");
        substitutes.put("who", "you");
        String original = "Hello ${name}, how are ${who}? What are you ${something}?";
        String expected = "Hello Alex, how are you? What are you ${something}?";
        String actual = template.generate(original, substitutes);

        assertThat(actual, is(expected));
    }

    /**
     * Test with spare keys in substitutes map.
     * @throws TemplateException TemplateException must be thrown.
     */
    @Test(expected = TemplateException.class)
    public void whenSubstituteMapHaveUnusedKeysThenExceptionThrown() throws TemplateException {
        Template template = new SimpleGenerator();
        Map<String, String> substitutes = new HashMap<>();
        substitutes.put("name", "Alex");
        substitutes.put("who", "you");
        substitutes.put("something", "doing");
        String original = "Hello ${name}, how are ${who}?";
        String expected = "Hello Alex, how are you? What are you doing?";

        String actual = template.generate(original, substitutes);

        assertThat(actual, is(expected));
    }

}