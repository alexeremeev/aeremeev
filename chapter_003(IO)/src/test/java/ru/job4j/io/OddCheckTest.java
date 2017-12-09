package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса OddCheck.
 */
public class OddCheckTest {

    /**
     * Проверка введенного числа на четность.
     */
    @Test
    public void whenInputIsOddNumberThenResultIsTrue() {
        OddCheck oddCheck = new OddCheck();
        String testString = "123456";
        InputStream stream = new ByteArrayInputStream(testString.getBytes(StandardCharsets.UTF_8));
        boolean result = oddCheck.isNumber(stream);
        assertThat(result, is(true));
    }

}