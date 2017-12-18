package ru.job4j.io.filesort;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Тест класса SortLargeFile.
 */
public class SortLargeFileTest {
    /**
     * Создаем 2 файла с текстом - оригинальный и ожидаемый. Проверяем корректность полного цикла работы программы.
     * @throws IOException IOException.
     */
    @Test
    public void whenSortFileThenGetSortedFile() throws IOException {
        String separator = System.getProperty("line.separator");
        StringBuilder original = new StringBuilder();
        original.append("Ulyanovsk man takes part in pancake eating contest, dies");
        original.append(separator);
        original.append("Italian man pretended to be mafia member to get free croissants");
        original.append(separator);
        original.append("Guest at a wedding in Yemen accidently shoots three people while dancing Gangam Style with an AK-47 rifle");
        original.append(separator);
        original.append("Man in China kills a neighbor before suicide to have a chess partner in afterlife");
        original.append(separator);
        original.append("Big round ears get a Himalayan bear cub stuck in a can");
        original.append(separator);
        original.append("Elderly woman attempts stealing alcohol for her birthday");
        original.append(separator);
        original.append("Polish coffin makers issue the latest erotic calendar");
        original.append(separator);
        original.append("Chubby hedgehog in Scotland is forced to exercise");
        original.append(separator);
        original.append("Policeman in Perm invents an abduction to explain skipped work days");
        original.append(separator);

        StringBuilder expected = new StringBuilder();
        expected.append("Chubby hedgehog in Scotland is forced to exercise");
        expected.append(separator);
        expected.append("Polish coffin makers issue the latest erotic calendar");
        expected.append(separator);
        expected.append("Big round ears get a Himalayan bear cub stuck in a can");
        expected.append(separator);
        expected.append("Elderly woman attempts stealing alcohol for her birthday");
        expected.append(separator);
        expected.append("Ulyanovsk man takes part in pancake eating contest, dies");
        expected.append(separator);
        expected.append("Italian man pretended to be mafia member to get free croissants");
        expected.append(separator);
        expected.append("Policeman in Perm invents an abduction to explain skipped work days");
        expected.append(separator);
        expected.append("Man in China kills a neighbor before suicide to have a chess partner in afterlife");
        expected.append(separator);
        expected.append("Guest at a wedding in Yemen accidently shoots three people while dancing Gangam Style with an AK-47 rifle");
        expected.append(separator);

        File originalFile = new File(System.getProperty("java.io.tmpdir") + "/originalTest.txt");
        FileWriter writer = new FileWriter(originalFile);
        writer.write(original.toString());
        writer.flush();
        writer.close();

        File expectedFile = new File(System.getProperty("java.io.tmpdir") + "/expectedTest.txt");
        FileWriter expectedWriter = new FileWriter(expectedFile);
        expectedWriter.write(expected.toString());
        expectedWriter.flush();
        expectedWriter.close();

        File actualFile = new File(System.getProperty("java.io.tmpdir") + "/actualTest.txt");

        new SortLargeFile().sort(originalFile, actualFile);

        byte[] actualBytes = Files.readAllBytes(Paths.get(actualFile.getAbsolutePath()));
        byte[] expectedBytes = Files.readAllBytes(Paths.get(expectedFile.getAbsolutePath()));

        String file1 = new String(actualBytes, StandardCharsets.UTF_8);
        String file2 = new String(expectedBytes, StandardCharsets.UTF_8);

        assertEquals("The content in the strings should match", file1, file2);
        actualFile.delete();
        originalFile.delete();
        expectedFile.delete();
    }
}