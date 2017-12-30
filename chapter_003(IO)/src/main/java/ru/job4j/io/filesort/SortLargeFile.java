package ru.job4j.io.filesort;

import java.io.File;

/**
 * class SortLargeFile - сортировка файла путем выделения его разметки и послеующей записи нового файла по ней.
 */
public class SortLargeFile implements SortFile {
    /**
     * Число строк для считывания из файла.
     */
    private static final int STRING_COUNT = 8192;
    /**
     * Число массивов разметки для разбития файла.
     */
    private static final int FILE_SPLIT_COUNT = 65536;

    @Override
    public void sort(File source, File destination) {

        ReadFile readFile = new ReadFile();
        ChunkSorting chunkSorting = new ChunkSorting();
        int index = 0;

        File[] files = new File[FILE_SPLIT_COUNT];
        do {

            long[][] chunkMapping = readFile.getChunkMapping(source, STRING_COUNT);
            chunkSorting.comparatorSort(chunkMapping);
            File temp = new File(System.getProperty("java.io.tmpdir") + "/0tmp" + index);
            files[index] = temp;
            chunkSorting.writeChunkMappingToFile(chunkMapping, temp);
            index++;

        } while (!readFile.isFinished());

        File resultMapping = chunkSorting.mergeArrayOfFiles(files);

        new WriteFile().writeSortedFileByMapping(source, resultMapping, destination);
    }
}
