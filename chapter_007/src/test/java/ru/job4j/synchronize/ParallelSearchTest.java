package ru.job4j.synchronize;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса ParallelSearch.
 */
public class ParallelSearchTest {
    /**
     * Тест поиска строки в xml.
     */
    @Test
    public void trySearch() {
        String text = "Finally, it is worth reading the documentation.";
        ArrayList<String> exts = new ArrayList<>(Arrays.asList("xml", "txt"));
        ParallelSearch ps = new ParallelSearch("C:/projects/", text, exts);
        Thread search = new Thread(ps);
        try {
            search.start();
            search.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        List<String> result = ps.getResult();
        List<String> expected = new ArrayList<>(Arrays.asList("C:\\projects\\aeremeev\\checkstyle.xml",
                "C:\\projects\\aeremeev\\chapter_007\\target\\checkstyle-checker.xml",
                "C:\\projects\\aeremeev\\target\\checkstyle-checker.xml"));
        for (String path : result) {
            System.out.println("Text found in: " + path);
        }
        assertThat(result, is(expected));
    }
}

