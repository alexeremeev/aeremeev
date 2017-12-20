package ru.job4j.socket;

import ru.job4j.io.chat.ConsoleIO;
import ru.job4j.io.chat.InputOutput;
import ru.job4j.io.chat.LogWriter;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * class Client - клиент для мудрого Оракла.
 */
public class Client {
    /**
     * Ключ выхода из чата.
     */
    private final static String EXIT = "пока";
    /**
     * Порт сервера.
     */
    private int serverPort = 4999;
    /**
     * Адрес сервера.
     */
    String host = "localhost";
    /**
     * Интерфейс ввода-вывода.
     */
    private InputOutput io;
    /**
     * Лог чата.
     */
    private final StringBuilder log = new StringBuilder();
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
     * @param io интерфейс ввода-вывода.
     * @param logFileName путь к файлу лога.
     */
    public Client(InputOutput io, String logFileName) {
        this.io = io;
        this.logFileName = logFileName;
    }

    /**
     * Инициализация клиента.
     * @return true, если соединение установлено.
     */
    public boolean init() {
        boolean result = false;
        try {
            System.out.println("Связываемся с Ораклом...");
            InetAddress inetAddress = InetAddress.getByName(host);
            Socket socket = new Socket(inetAddress, serverPort);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            System.out.println("Оракл на месте, можете говорить...");
            result = true;
        } catch (IOException ioe) {
            System.out.println("Оракла нет на месте...");
            result = false;
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
        this.io.print("user: ");
        try {
            do {
                send = this.io.read();
                log.append(String.format("user: %s%s", send, separator));
                this.output.writeUTF(send);
                received = input.readUTF();
                this.io.println(received);
                log.append(String.format("%s%s", received, separator));
                this.io.print("user: ");
            } while (!send.equalsIgnoreCase(EXIT));
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
        Client client = new Client(new ConsoleIO(new BufferedReader(new InputStreamReader(System.in)), System.out), "userlog.txt");
        client.init();
        client.exchange();
    }
}
