package ru.job4j.filefinder;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Тесты класса FileFinder.
 */
public class FileFinderTest {
    /**
     * Тест поиска по маске. Ищем все *.txt файлы.
     * @throws IOException
     */
    @Test
    public void tryTest() throws IOException {
        List<String> expected = new ArrayList<>(Arrays.asList("Files found: 3",
                "C:\\projects\\aeremeev\\chat.txt",
                "C:\\projects\\aeremeev\\settings.txt",
                "C:\\projects\\aeremeev\\wiseman.txt"));
        String pattern = "glob:*.txt";
        FileFinder finder = new FileFinder(pattern, "C:/projects/aeremeev", "filefinder.log");
        finder.init();
        Path logPath = Paths.get("C:/projects/aeremeev/chapter_003(IO)", "filefinder.log");
        List<String> actual = Files.readAllLines(logPath, StandardCharsets.UTF_8);
        assertThat(actual, is(expected));
    }

    /**
     * Тест поиска конкретного файла. Ищем log4j.properties.
     * @throws IOException IOException.
     */
    @Test
    public void findByStrong() throws IOException {
        List<String> expected = new ArrayList<>(Arrays.asList("Files found: 2",
                "C:\\projects\\aeremeev\\chapter_008\\resources\\log4j.properties",
                "C:\\projects\\aeremeev\\chapter_009_dao\\src\\main\\resources\\log4j.properties"));
        String pattern = "glob:log4j.properties";
        FileFinder finder = new FileFinder(pattern, "C:/projects/aeremeev", "filefinder.log");
        finder.init();
        Path logPath = Paths.get("C:/projects/aeremeev/chapter_003(IO)", "filefinder.log");
        List<String> actual = Files.readAllLines(logPath, StandardCharsets.UTF_8);
        assertThat(actual, is(expected));
    }

    /**
     * Тест поиска по регулярному выражению. Ищем все *.js и *.txt файлы.
     * @throws IOException IOException.
     */
    @Test
    public void findByregex() throws IOException {
        List<String> expected = new ArrayList<>(Arrays.asList("Files found: 7",
                "C:\\projects\\aeremeev\\chapter_009\\src\\main\\webapp\\js\\address.js",
                "C:\\projects\\aeremeev\\chapter_009\\src\\main\\webapp\\WEB-INF\\views\\js\\countryJson.js",
                "C:\\projects\\aeremeev\\chapter_009\\src\\main\\webapp\\WEB-INF\\views\\js\\verifyForm.js",
                "C:\\projects\\aeremeev\\chapter_009_dao\\src\\main\\webapp\\WEB-INF\\views\\js\\scripts.js",
                "C:\\projects\\aeremeev\\chat.txt",
                "C:\\projects\\aeremeev\\settings.txt",
                "C:\\projects\\aeremeev\\wiseman.txt"));
        String pattern  = "regex:^.*(([^\\.][\\.][jJ][sS])|([^\\.][\\.][tT][xX][tT]))$";
        FileFinder finder = new FileFinder(pattern, "C:/projects/aeremeev", "filefinder.log");
        finder.init();
        Path logPath = Paths.get("C:/projects/aeremeev/chapter_003(IO)", "filefinder.log");
        List<String> actual = Files.readAllLines(logPath, StandardCharsets.UTF_8);
        assertThat(actual, is(expected));
    }
}