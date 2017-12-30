package ru.job4j.socket;

import ru.job4j.io.chat.LogWriter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * class Server - сервер для мудрого Оракла.
 */
public class Server {
    /**
     * Ключ выхода из чата.
     */
    private final static String EXIT = "пока";
    /**
     * Ключ приветствия.
     */
    private final static String HELLO = "привет";
    /**
     * Ключ выдачи цитаты.
     */
    private final static String WISDOM = "поделись";
    /**
     * Random.
     */
    private final static Random RN = new Random();
    /**
     * Порт сервера.
     */
    private final int port = 4999;
    /**
     * Лог чата.
     */
    private final StringBuilder log = new StringBuilder();
    /**
     * Сообщения - цитаты.
     */
    private List<String> messages;
    /**
     * Путь к файлу цитат.
     */
    private String chatFileName;
    /**
     * Путь к файлу лога.
     */
    private String logFileName;
    /**
     * Поток ввода.
     */
    private DataInputStream input;
    /**
     * Поток вывода.
     */
    private DataOutputStream output;

    /**
     * Конструктор.
     * @param chatFileName путь к файлу цитат.
     * @param logFileName путь к файлу лога.
     */
    public Server(String chatFileName, String logFileName) {
        this.chatFileName = chatFileName;
        this.logFileName = logFileName;
    }

    /**
     * Инициализация сервера.
     * @return true, если соединение установлено.
     */
    public boolean init() {
        boolean result = false;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Оракл ожидает гостя...");
            this.messages = Files.readAllLines(Paths.get(this.chatFileName), StandardCharsets.UTF_8);
            Socket socket = serverSocket.accept();
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            result = true;
            System.out.println("Гость пришел...");
        } catch (IOException ioe) {
            result = false;
            System.out.println("Что-то пошло не так...");
            ioe.printStackTrace();
        }
        return result;
    }

    /**
     * Обмен сообщениями.
     */
    public void exchange() {
        String received;
        String send;
        String separator = System.getProperty("line.separator");
        try {

            do {
                received = this.input.readUTF();
                log.append(String.format("user: %s%s", received, separator));
                if (received.equalsIgnoreCase(HELLO)) {
                    send = "Приветствую тебя мой друг, я Оракл.";
                    log.append(String.format("oracle: %s%s", send, separator));
                    output.writeUTF(String.format("oracle: %s", send));
                } else if (received.contains(WISDOM)) {
                    send = messages.get(RN.nextInt(messages.size()));
                    log.append(String.format("oracle: %s%s", send, separator));
                    output.writeUTF(String.format("oracle: %s", send));
                } else {
                    send = "Я не понимаю тебя, друг мой, возможно нам стоит попрощаться.";
                    log.append(String.format("oracle: %s%s", send, separator));
                    output.writeUTF(String.format("oracle: %s", send));
                }
            } while (!EXIT.equalsIgnoreCase(received));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            LogWriter logWriter = new LogWriter(this.logFileName);
            logWriter.write(log.toString());
        }
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        Server server = new Server("wiseman.txt", "wiselog.txt");
        server.init();
        server.exchange();
    }

}
