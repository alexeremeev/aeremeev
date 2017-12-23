package ru.job4j.filemanager;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * class Server - текстовый файловый сервер.
 * Параметры задаются в файле filemanager.properties.
 */
public class Server {
    /**
     * Инициализация.
     */
    public void init() {
        Settings settings = new Settings("filemanager.properties");
        int port = Integer.valueOf(settings.getSettings("Port"));
        String rootDir = settings.getSettings("RootDir");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Awaiting connection...");
            Socket socket = serverSocket.accept();
            System.out.println("Connection accepted..");
            Manager manager = new Manager(new File(rootDir), socket);
            Menu menu = new Menu(socket);
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
        new Server().init();
    }
}
