package ru.job4j.io.filesort;

import java.io.*;

/**
 * class WriteFile - запись файла.
 */
public class WriteFile {
    /**
     * Записывает новый файл по сортированной разметке исходного.
     * @param source исходный файл.
     * @param mapping разметка.
     * @param destination файл назначения.
     */
    public void writeSortedFileByMapping(File source, File mapping, File destination) {
        String sourceLine = null;
        String separator = System.getProperty("line.separator");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(mapping));
            RandomAccessFile randomAccessFile = new RandomAccessFile(source, "r");
            FileWriter fileWriter = new FileWriter(destination);
            while ((sourceLine = bufferedReader.readLine()) != null) {
                long byteIndex = Long.valueOf(sourceLine.split(" ")[1]);
                randomAccessFile.seek(byteIndex);
                String destLine = randomAccessFile.readLine();
                if (destLine != null) {
                    fileWriter.write(destLine);
                }
                fileWriter.write(separator);
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
