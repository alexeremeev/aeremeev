package ru.job4j.filemanager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * class Client - текстовый файловый клиент.
 * Параметры задаются в файле filemanager.properties.
 */
public class Client {
    /**
     * Инициализация.
     */
    public void init() {
        Settings settings = new Settings("filemanager.properties");
        String address = settings.getSettings("IpAddress");
        int port = Integer.valueOf(settings.getSettings("Port"));
        System.out.println("Connecting...");
        try (Socket socket = new Socket(InetAddress.getByName(address), port)) {
            System.out.println("Connected...");
            Manager manager = new Manager(null, socket);
            Menu menu = new Menu();
            menu.init(manager);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        new Client().init();
    }
}
