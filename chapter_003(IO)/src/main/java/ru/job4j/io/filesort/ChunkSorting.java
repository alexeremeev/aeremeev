package ru.job4j.io.filesort;

import java.io.*;
import java.util.Scanner;

/**
 * class ChunkSorting - сортировка и обработка массивов, полученных из ReadFile.getChunkMapping().
 */
public class ChunkSorting {

    private final static String SEPARATOR = System.getProperty("line.separator");
    /**
     * Сортировка с помощью компаратора.
     * @param chunkMapping long[][]chunkMapping.
     */
    public void comparatorSort(long[][]chunkMapping) {
        java.util.Arrays.sort(chunkMapping, java.util.Comparator.comparingLong(a -> a[0]));
    }

    /**
     * Записывает массив во временный файл.
     * @param chunkMapping входной массив [длина строки][номер байта начала строки].
     * @param file временный файл.
     */
    public void writeChunkMappingToFile(long[][]chunkMapping, File file) {
        StringBuilder builder = new StringBuilder();
        try (FileWriter writer = new FileWriter(file)) {
            for (long[] line : chunkMapping) {
                if (line[0] != 0 || line[1] != 0) {
                    builder.append(String.format("%d %d", line[0], line[1]));
                    builder.append(System.getProperty("line.separator"));
                }
            }
            writer.append(builder.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    /**
     * Сливает 2 файла разметки в один, отсортированный по возрастанию значения [длина строки].
     * @param firstFile первый файл.
     * @param secondFile второй файл.
     * @param result выходной файл.
     */
    public void mergeFiles(File firstFile, File secondFile, File result) {
        boolean repeat = true;
        boolean readFirst = true;
        boolean readSecond = true;
        Chunk left = new Chunk(0, 0);
        Chunk right = new Chunk(0, 0);
        try (Scanner firstBuffer = new Scanner(firstFile);
             Scanner secondBuffer = new Scanner(secondFile);
             FileWriter writer = new FileWriter(result)) {
            while (repeat) {
                if (readFirst) {
                    if (firstBuffer.hasNextLong()) {
                        left.stringLength = firstBuffer.nextLong();
                        left.stringMarker = firstBuffer.nextLong();
                    }
                    readFirst = false;
                }
                if (readSecond) {
                    if (secondBuffer.hasNextLong()) {
                        right.stringLength = secondBuffer.nextLong();
                        right.stringMarker = secondBuffer.nextLong();
                    }
                    readSecond = false;
                }
                if (left.stringLength != 0 && right.stringLength != 0) {
                    if (left.stringLength < right.stringLength) {
                        writer.append(String.format("%d %d", left.stringLength, left.stringMarker));
                        writer.append(SEPARATOR);
                        left.setDefault();
                        readFirst = true;
                    } else if (left.stringLength > right.stringLength) {
                        writer.append(String.format("%d %d", right.stringLength, right.stringMarker));
                        writer.append(SEPARATOR);
                        right.setDefault();
                        readSecond = true;
                    } else {
                        writer.append(String.format("%d %d", left.stringLength, left.stringMarker));
                        writer.append(SEPARATOR);
                        writer.append(String.format("%d %d", right.stringLength, right.stringMarker));
                        writer.append(SEPARATOR);
                        left.setDefault();
                        right.setDefault();
                        readFirst = true;
                        readSecond = true;
                    }
                } else if (left.stringLength != 0) {
                    writer.append(String.format("%d %d", left.stringLength, left.stringMarker));
                    writer.append(SEPARATOR);
                    left.setDefault();
                    readFirst = true;
                } else if (right.stringLength != 0) {
                    writer.append(String.format("%d %d", right.stringLength, right.stringMarker));
                    writer.append(SEPARATOR);
                    right.setDefault();
                    readSecond = true;
                } else {
                    repeat = false;
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Собирает все полученные файлы массивов разметки в один итоговый.
     * @param files файлы раметки.
     * @return итоговый файл разметки.
     */
    public File mergeArrayOfFiles(File[] files) {
        boolean repeat = true;
        File firstFile = null;
        File secondFile = null;
        int counter = 1;

        while (repeat) {
            repeat = false;
            for (int i = 0; i != files.length; i++) {
                if (firstFile == null) {
                    if (files[i] != null) {
                        firstFile = files[i];
                        files[i] = null;
                    }
                } else {
                    if (files[i] != null) {
                        secondFile = files[i];
                        files[i] = null;
                    }
                }
                if (firstFile != null && secondFile != null) {
                    String tempFilename = System.getProperty("java.io.tmpdir") + "/" + counter + "temp";
                    File tempFile = new File(tempFilename);
                    this.mergeFiles(firstFile, secondFile, tempFile);
                    files[i] = tempFile;
                    firstFile.delete();
                    secondFile.delete();
                    firstFile = null;
                    secondFile = null;
                    repeat = true;
                    counter++;
                }
                if (i == files.length - 1 && firstFile != null) {
                    files[i] = firstFile;
                    firstFile = null;
                }
            }

        }
        return files[files.length - 1];
    }

    private class Chunk {

        private long stringLength;

        private long stringMarker;

        public Chunk(long stringLength, long stringMarker) {
            this.stringLength = stringLength;
            this.stringMarker = stringMarker;
        }

        public void setDefault() {
            this.setStringLength(0);
            this.setStringMarker(0);
        }

        public void setStringLength(long stringLength) {
            this.stringLength = stringLength;
        }

        public void setStringMarker(long stringMarker) {
            this.stringMarker = stringMarker;
        }

        @Override
        public String toString() {
            return String.format("Chunk: %d %d", this.stringLength, this.stringMarker);
        }
    }
}
