package ru.job4j.jdbc;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты класса Database.
 */
public class DatabaseTest {
    /**
     * Тест правильности вычислений на выборке из 100 рядов.
     */
    @Test
    public void whenProcessDatabaseThenRecieveCorrectResult() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String expected = "5050\r\n";

        new Database().init(100);

        assertThat(out.toString(), is(expected));
    }

    /**
     * Тест скорости работы на выборке в 1 млн.
     * WARNING! RUNTIME ~ 2.5 minutes.
     */
    @Test
    public void whenProcessLargeNumberOfEntriesInDatabaseThenRuntimeIsLessThenFiveMinutes() {
        long start = System.currentTimeMillis();
        new Database().init(1000000);
        long actual = System.currentTimeMillis() - start;
        long expected = 300000;

        assertThat(actual < expected, is(true));
    }
}
