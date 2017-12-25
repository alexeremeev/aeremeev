package ru.job4j.io.archive;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * class SimpleZip - простой архиватор zip. Запускается с обязательными параметрами из командной строки.
 *  -d,--destination <arg>   input directory destination.
 *  -e,--extensions <arg>    file extensions to archive.
 *  -o,--output <arg>        path to output file.
 */
public class SimpleZip {
    /**
     * Список файлов для архивации.
     */
    private List<String> fileList;
    /**
     * Корневая директория для архивации.
     */
    private String sourceFolder;
    /**
     * Массив расширений файлов для архивации.
     */
    private String[] ext;

    /**
     * Конструктор.
     * @param sourceFolder корневая директория для архивации.
     * @param ext массив расширений файлов для архивации.
     */
    public SimpleZip(String sourceFolder, String[] ext) {
        this.sourceFolder = sourceFolder;
        this.ext = ext;
        this.fileList = new ArrayList<>(100);
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        Options options = new Options();

        Option dest = new Option("d", "destination", true, "input directory destination");
        dest.setRequired(true);
        options.addOption(dest);

        Option exts = Option.builder("e").
                longOpt("extensions").
                desc("file extensions to archive").
                valueSeparator(',').
                hasArgs().build();
        exts.setRequired(true);
        options.addOption(exts);

        Option output = new Option("o", "output", true, "path to output file");
        output.setRequired(true);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("SimpleZip", options);

            return;
        }

        String sourceFolder = cmd.getOptionValue("destination");
        String outputZipFile = cmd.getOptionValue("output");

        String[] extensions = cmd.getOptionValues("extensions");

        SimpleZip simpleZip = new SimpleZip(sourceFolder, extensions);
        simpleZip.generateFileList(new File(sourceFolder), extensions);
        simpleZip.zipSource(outputZipFile);


    }

    /**
     * Архивирует файлы из fileList в zipFile.
     * @param zipFile путь к файлу назначения.
     */
    public void zipSource(String zipFile) {

        byte[] buffer = new byte[1024];

        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            System.out.println(String.format("Input folder: %s", this.sourceFolder));
            System.out.println(String.format("Output to Zip : %s", zipFile));

            for (String file : this.fileList) {

                System.out.println(String.format("File Added : %s", file));
                ZipEntry ze = new ZipEntry(file);
                zos.putNextEntry(ze);

                try (FileInputStream in = new FileInputStream(String.format("%s%s%s", this.sourceFolder, File.separator, file))) {
                    int len;
                    while ((len = in.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                }
            }
            zos.closeEntry();
            System.out.println("Done");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Получеает список файлов для архивации. Список сохраняется в fileList.
     * @param node файл или директория.
     * @param exts расширения файлов для архивации.
     */
    public void generateFileList(File node, String[] exts) {

        if (node.isFile()) {
            for (String extension: exts) {
                if (this.getFileExtension(node).equals(extension)) {
                    this.fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
                    break;
                }
            }
        }

        if (node.isDirectory()) {
            String[] subNode = node.list();
            for (String filename : subNode) {
                generateFileList(new File(node, filename), exts);
            }
        }
    }

    /**
     * Получает форматированный путь файла для архиватора.
     * @param file исходный путь файла.
     * @return форматированный путь файла для архиватора.
     */
    private String generateZipEntry(String file) {
        return file.substring(this.sourceFolder.length() + 1, file.length());
    }

    /**
     * Получает расширение файла.
     * @param file файл.
     * @return строка с расширением файла.
     */
    private String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
}