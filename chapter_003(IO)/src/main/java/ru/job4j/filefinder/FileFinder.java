package ru.job4j.filefinder;

import org.apache.commons.cli.*;
import ru.job4j.io.chat.LogWriter;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Class FileFinder - консольное приложение поиска файла по условию.
 */
public class FileFinder extends SimpleFileVisitor<Path> {
    /**
     * Разделитель строк.
     */
    private final static String SEPARATOR = System.getProperty("line.separator");
    /**
     * Корневая директория для поиска.
     */
    private String sourceFolder;
    /**
     * Имя лог файла.
     */
    private String logFileName;
    /**
     * Строка лога.
     */
    private StringBuilder logger;
    /**
     * PathMatcher.
     */
    private PathMatcher matcher;
    /**
     * Результирующий список файлов.
     */
    private List<Path> result;

    /**
     * Конструктор.
     * @param pattern паттерн для поиска, имя файла, маска, регулярное выражение.
     * @param sourceFolder корневая директория для поиска.
     * @param logFileName имя лог файла.
     */
    FileFinder(String pattern, String sourceFolder, String logFileName) {
        this.matcher = FileSystems.getDefault().getPathMatcher(pattern);
        this.sourceFolder = sourceFolder;
        this.logFileName = logFileName;
        this.logger = new StringBuilder();
        this.result = new ArrayList<>(100);
    }

    /**
     * Сравнивает имя файла с паттерном.
     * @param file файл.
     */
    private void match(Path file) {
        Path name = file.getFileName();
        if (name != null && matcher.matches(name)) {
            this.result.add(file);
        }
    }

    /**
     * Вызывает метод Match для посещенного файла.
     * @param file файл.
     * @param attrs базовые аттрибуты файла.
     * @return CONTINUE, продолжает обхода древа файлов, каталогов.
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        this.match(file);
        return CONTINUE;
    }

    /**
     * Вызывает метод Match для посещенного каталога.
     * @param dir каталог.
     * @param attrs базовые аттрибуты каталога.
     * @return CONTINUE, продолжает обхода древа файлов, каталогов.
     */
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        this.match(dir);
        return CONTINUE;
    }

    /**
     * Вызывается при ошибке в ходе посещения файла.
     * @param file файл.
     * @param ioe IOException.
     * @return CONTINUE, продолжает обхода древа файлов, каталогов.
     */
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException ioe) {
        ioe.printStackTrace();
        return CONTINUE;
    }

    /**
     * Инициализация поиска, получения списка найденных файлов и запись его в лог.
     */
    public void init() {
        try {
            Files.walkFileTree(Paths.get(this.sourceFolder), this);
            System.out.println(String.format("Files found: %s", this.result.size()));
            logger.append(String.format("Files found: %s%s", this.result.size(), SEPARATOR));

            for (Path path : this.result) {
                System.out.println(path.toAbsolutePath().toString());
                logger.append(String.format("%s%s", path.toAbsolutePath().toString(), SEPARATOR));
            }
            LogWriter logWriter = new LogWriter(this.logFileName);
            logWriter.write(logger.toString());
            System.out.println(String.format("Results were written in log: %s", this.logFileName));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Main.
     * @param args args provided by Apache CLI.
     */
    public static void main(String[] args) {
        Options options = new OptionsBuilder().getArguments();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("FileFinder.jar -d c:/ -n *.txt -m -o log.txt", options);
            return;
        }

        String sourceFolder = cmd.getOptionValue("directory");
        String fileName = cmd.getOptionValue("filename");
        String logFileName = cmd.getOptionValue("output");
        String pattern;

        if (cmd.hasOption("m") || cmd.hasOption("f")) {
            pattern = String.format("glob:%s", fileName);
        } else if (cmd.hasOption("r")) {
            pattern = String.format("regex:%s", fileName);
        } else {
            formatter.printHelp("FileFinder.jar -d c:/ -n *.txt -m -o log.txt", options);
            return;
        }

        FileFinder finder = new FileFinder(pattern, sourceFolder, logFileName);
        finder.init();
    }
}
