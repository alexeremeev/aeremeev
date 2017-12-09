package ru.job4j.io;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса Filter.
 */
public class FilterTest {

    /**
     * Тест замены слов.
     * @throws IOException IOException.
     */
    @Test
    public void whenInputHaveAbusesThenOutputHaveNot() throws IOException {
        String original = "Не хочу ничего делать, хочу лежать на диване, смотреть телевизор."
               + "Ну может быть еще пива выпить!";
        String[] abuses = new String[] {"делать", "диване", "телевизор", "пива"};
        String expected = "Не хочу ничего *, хочу лежать на *, смотреть *."
               + "Ну может быть еще * выпить!";
        InputStream inputStream = new ByteArrayInputStream(original.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        new Filter().dropAbuses(inputStream, outputStream, abuses);
        byte[] bytes = outputStream.toByteArray();

        String result = new String(bytes);

        assertThat(result, is(expected));
    }
}