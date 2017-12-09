package ru.job4j.io;

import java.io.*;

/**
 * class Filter - фильтрация слов во входном потоке.
 */
public class Filter {
    /**
     * Метод удаляет слова из входного потока. В выходном потоке они заменены на '*'.
     * @param in входной поток.
     * @param out выходной поток.
     * @param abuses массив слов для замены.
     * @throws IOException IOException.
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuses) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (String abuse: abuses) {
                    line = line.replaceAll(abuse, "*");
                }
                out.write(line.getBytes());
            }
        }
    }
}
