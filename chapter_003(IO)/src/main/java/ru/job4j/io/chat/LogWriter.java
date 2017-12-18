package ru.job4j.io.chat;

import java.io.*;

/**
 * class LogWriter - запись лога чата.
 */
public class LogWriter {
    /**
     * BufferedWriter.
     */
    private BufferedWriter writer;

    /**
     * Конструктор.
     * @param logFilePath путь к файлу лога.
     */
    public LogWriter(String logFilePath) {
        File logFile = new File(logFilePath);
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            this.writer = new BufferedWriter(new FileWriter(logFile));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Записывает сообщения чата в лог.
     * @param chatMessage сообщение чата.
     */
    public void write(String chatMessage) {
        try {
            writer.write(chatMessage);
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
