package ru.job4j.softcache;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SoftCache - cache based on Map interface and soft references.
 * @author aeremeev.
 * @version 1
 * @since 25.01.2018
 */
public class SoftCacheTest {

    private final String separator = System.getProperty("line.separator");

    /**
     * Test of adding and getting value from cache.
     */
    @Test
    public void whenRequestStringFromCacheThenGetExpectedString() {
        SoftCache cache = new SoftCache("C:/projects/aeremeev/chapter_006");
        StringBuilder expected = new StringBuilder();
        expected.append("Alex").append(separator).append("Peter").append(separator);
        expected.append("Dimitry").append(separator).append("Anna").append(separator);
        expected.append("Rose").append(separator);

        String result = cache.getValue("Names.txt");

        assertThat(result, is(expected.toString()));
    }

}