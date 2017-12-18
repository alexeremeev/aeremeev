package ru.job4j.io.chat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * class Chat - консольный чат с ботом.
 */
public class Chat {
    /**
     * STOP bot respond.
     */
    private final static String STOP = "стоп";
    /**
     * CONTINUE bot respond.
     */
    private final static String CONTINUE = "продолжить";
    /**
     * EXIT from chat.
     */
    private final static String EXIT = "закончить";
    /**
     * Random.
     */
    private final static Random RN = new Random();
    /**
     * Сообщения чата.
     */
    private final StringBuilder chatMessages = new StringBuilder();
    /**
     * Путь к файлу сообщений бота.
     */
    private String chatFileName;
    /**
     * Путь к файлу лога чата.
     */
    private String logFileName;
    /**
     * IO.
     */
    private InputOutput io;
    /**
     * Сообщения бота.
     */
    private List<String> messages;

    /**
     * Конструктор.
     * @param io IO.
     * @param chatFileName путь к файлу сообщений бота.
     * @param logFileName путь к файлу лога чата.
     */
    public Chat(InputOutput io, String chatFileName, String logFileName) {
        this.io = io;
        this.chatFileName = chatFileName;
        this.logFileName = logFileName;
    }

    /**
     * Обмен сообщениями.
     */
    public void exchangeMessages() {
        try {
            this.messages = Files.readAllLines(Paths.get(this.chatFileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String output;
        boolean respond = true;
        String separator = System.getProperty("line.separator");
        this.io.println("Let's talk");
        this.io.print("user: ");
        String input = this.io.read();
        while (!input.equalsIgnoreCase(EXIT)) {
            this.chatMessages.append(String.format("user: %s%s", input, separator));
            if (input.equalsIgnoreCase(STOP)) {
                respond = false;
            }
            if (input.equalsIgnoreCase(CONTINUE)) {
                respond = true;
            }
            if (respond) {
                output = this.messages.get(RN.nextInt(messages.size()));
                this.chatMessages.append(String.format("bot: %s%s", output, separator));
                this.io.println(String.format("bot: %s", output));
            }
            this.io.print("user: ");
            input = this.io.read();
        }
        LogWriter logWriter = new LogWriter(this.logFileName);
        logWriter.write(chatMessages.toString());
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        Chat chat = new Chat(new ConsoleIO(new BufferedReader(new InputStreamReader(System.in)), System.out),
                "chat.txt", "log.txt");
        chat.exchangeMessages();
    }
}
