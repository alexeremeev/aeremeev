package ru.job4j.synchronize;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * class ParallelSearch - параллельный поиск текста в файлах.
 */
public final class ParallelSearch {
    /**
     * Корневая папка.
     */
    private final String root;
    /**
     * Текст для поиска.
     */
    private final String text;
    /**
     * Список расширений файлов, в которых нужно искать.
     */
    private final List<String> exts;
    /**
     * Список файлов, где найден текст.
     */
    private final CopyOnWriteArrayList<String> result = new CopyOnWriteArrayList<>();
    /**
     * Получаем количество тредов CPU.
     */
    //CHECKSTYLE.OFF
    private static final int CPU_THREADS = Runtime.getRuntime().availableProcessors();
    //CHECKSTYLE.ON
    /**
     * Тред пул.
     */
    private final ExecutorService executorService = Executors.newFixedThreadPool(CPU_THREADS);


    /**
     * Конструктор.
     * @param root корневая папка.
     * @param text текст для поиска.
     * @param exts расширения файлов, в которых нужно искать.
     */
    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    /**
     * Возвращает список результатов поиска.
     * @return список результатов поиска.
     */
    public List<String> getResult() {
        List<String> list = new ArrayList<>(result);
        return list;
    }

    /**
     * Поиск всех вложенных подпапок в директории root.
     * @param root корневая папка.
     * @param dirs список для записи поддиректорий.
     */
    private void searchDir(File root, List<String> dirs) {
        try {
           final File[] files = root.listFiles();
            for (File file: files) {
                if (file.isDirectory()) {
                    dirs.add(file.getCanonicalPath());
                    searchDir(file, dirs);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Фильтр файлов по списку расширений exts.
     */
    private class GenericExtFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            for (String ext: exts) {
                if (name.endsWith(ext)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Возвращает массив файлов из директории dirName по фильтру exts.
     * @param dirName директория.
     * @return массив файлов из директории dirName по фильтру exts.
     */
    private File[] finder(String dirName) {
        final File dir = new File(dirName);
        return dir.listFiles(new GenericExtFilter());
    }

    /**
     * Поиск заданного текста в файле и добавление его пути в result.
     * @param file файл.
     * @param text текст.
     * @return true, если найден.
     */
    private boolean searchText(File file, String text) {
        boolean searchResult = false;
        if (readFile(file).contains(text)) {
            result.add(file.getPath());
            searchResult = true;
        }
        return searchResult;
    }

    /**
     * Создает из файла String.
     * @param file файл.
     * @return String.
     */
    private static String readFile(File file) {
        final StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

    /**
     * 1. Запускаем метод run().
     * 2. Получаем список полных путей всех поддиректорий в корне поиска.
     * 3. Для каждой поддиректории запускаем по отдельному треду.
     *    В них ищем все файлы по маске расширения exts.
     *    Преобразуем каждый файл в строку и в ней ищем заданный текст.
     *    Если текст найден - добавляем в список result.
     *
     */
    public void find() {
        final ArrayList<String> dirs = new ArrayList<>();
        searchDir(new File(this.root), dirs);
        for (String dir : dirs) {
            executorService.execute(new Thread(() -> {
                File[] filesByExt = finder(dir);
                for (File file: filesByExt) {
                    searchText(file, text);
                }
            }));
        }
        try {
            executorService.shutdown();
            executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);

        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}

