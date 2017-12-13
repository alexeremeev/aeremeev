package ru.job4j.io.filesort;

import java.io.*;

/**
 * class ReadFile - чтение текстового файла.
 */
public class ReadFile {
    /**
     * FileReader.
     */
    private FileReader fileReader;
    /**
     * BufferedReader.
     */
    private BufferedReader bufferedReader;
    /**
     * Байт начала строки в файле.
     */
    private long cursor;
    /**
     * Флаг завершения чтения файла.
     */
    private boolean finished;

    /**
     * Конструктор.
     * @param file файл для чтения.
     */
    public ReadFile(File file) {
        try {
            this.fileReader = new FileReader(file);
            this.bufferedReader = new BufferedReader(this.fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Геттер finished.
     * @return finished.
     */
    public boolean isFinished() {
        return this.finished;
    }

    /**
     * Считать часть файла и получить его разметку в виде двумерного массива.
     * [длина строки][номер байта начала строки]. Чтение продолжается до тех пор,
     * пока не установлен флаг isFinished в конце файла.
     * @param chunkSize количество строк для считывания.
     * @return массив [длина строки][номер байта начала строки].
     * @throws IOException IOException.
     */
    public long[][] getChunkMapping(int chunkSize) throws IOException {
        long[][] result = new long[chunkSize][2];
        String line;
        long stringLength;
        int countOfStrings = 0;

        while ((line = this.bufferedReader.readLine()) != null) {
            this.cursor = this.cursor + line.getBytes().length + 2; // +2 offset of \r\n
            stringLength = line.getBytes().length + 2;
            result[countOfStrings][0] = stringLength;
            result[countOfStrings][1] = this.cursor - stringLength;

            countOfStrings++;

            if (countOfStrings >= chunkSize) {
                break;
            }
        }
        if (line == null) {
            this.finished = true;
            this.fileReader.close();
            this.bufferedReader.close();
        }
        return result;
    }
}
