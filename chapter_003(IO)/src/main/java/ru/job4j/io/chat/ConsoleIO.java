package ru.job4j.io.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * class ConsoleIO - ввод/вывод с помощью консоли.
 */
public class ConsoleIO implements InputOutput {
    /**
     * BufferedReader.
     */
    private BufferedReader reader;
    /**
     * PrintStream.
     */
    private PrintStream stream;

    /**
     * Конструктор.
     * @param reader BufferedReader.
     * @param stream PrintStream.
     */
    public ConsoleIO(BufferedReader reader, PrintStream stream) {
        this.reader = reader;
        this.stream = stream;
    }

    /**
     * Считывает строку, введенную пользователем.
     * @return строка, введенная пользователем.
     */
    @Override
    public String read() {
        String result = "";
        try {
            result = this.reader.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    /**
     * Вывести сообщение с переводом на новую строку.
     * @param message сообщение.
     */
    @Override
    public void println(String message) {
        this.stream.println(message);
    }

    /**
     * Вывести сообщение.
     * @param message сообщение.
     */
    @Override
    public void print(String message) {
        this.stream.print(message);
    }
}
