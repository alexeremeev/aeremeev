package ru.job4j.io.filesort;

import java.io.File;
import java.io.IOException;

/**
 * interface SortFile - сортировка файла по длине строк и запись нового.
 */
public interface SortFile {
    /**
     * Сортирует файл по длине строк и записывает новый.
     * @param source исходный файл.
     * @param destination файл назначения.
     */
    void sort(File source, File destination);
}
