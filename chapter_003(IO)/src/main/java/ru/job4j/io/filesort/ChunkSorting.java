package ru.job4j.io.filesort;

import java.io.*;

/**
 * class ChunkSorting - сортировка и обработка массивов, полученных из ReadFile.getChunkMapping().
 */
public class ChunkSorting {
    /**
     * Сортировка с помощью компаратора.
     * @param chunkMapping long[][]chunkMapping.
     */
    public void comparatorSort(long[][]chunkMapping) {
        java.util.Arrays.sort(chunkMapping, java.util.Comparator.comparingLong(a -> a[0]));
    }

    /**
     * Сортировка методом Шелла по значению первого индекса(длина строки).
     * @param chunkMapping входной массив [длина строки][номер байта начала строки].
     */
    public void shellSort(long[][]chunkMapping) {
        int inner;
        int outer;
        long[] temp;
        int step = 1;
        while (step <= chunkMapping.length / 3) {
            step = step * 3 + 1;
        }

        while (step > 0) {
            for (outer = step; outer < chunkMapping.length; outer++) {
                temp = chunkMapping[outer];
                inner = outer;

                while (inner > step - 1 && chunkMapping[inner - step][0] >= temp[0]) {
                    chunkMapping[inner] = chunkMapping[inner - step];
                    inner -= step;
                }
                chunkMapping[inner] = temp;
            }
            step = (step - 1) / 3;
        }
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

        String firstString = null;
        String secondString = null;
        try (BufferedReader firstBuffer = new BufferedReader(new FileReader(firstFile));
             BufferedReader secondBuffer = new BufferedReader(new FileReader(secondFile));
             FileWriter writer = new FileWriter(result)) {
            while (repeat) {
                if (readFirst) {
                    firstString = firstBuffer.readLine();
                    readFirst = false;
                }
                if (readSecond) {
                    secondString = secondBuffer.readLine();
                    readSecond = false;
                }
                if (firstString != null && secondString != null) {
                    if (Long.parseLong(firstString.split(" ")[0]) < Long.parseLong(secondString.split(" ")[0])) {
                        writer.append(firstString);
                        writer.append(System.getProperty("line.separator"));
                        readFirst = true;
                    } else if (Long.parseLong(firstString.split(" ")[0]) > Long.parseLong(secondString.split(" ")[0])) {
                        writer.append(secondString);
                        writer.append(System.getProperty("line.separator"));
                        readSecond = true;
                    } else {
                        writer.append(firstString);
                        writer.append(System.getProperty("line.separator"));
                        writer.append(secondString);
                        writer.append(System.getProperty("line.separator"));
                        readFirst = true;
                        readSecond = true;
                    }
                } else if (firstString != null) {
                    writer.append(firstString);
                    writer.append(System.getProperty("line.separator"));
                    readFirst = true;
                } else if (secondString != null) {
                    writer.append(secondString);
                    writer.append(System.getProperty("line.separator"));
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


}
