package ru.job4j.filemanager;

import java.io.*;
import java.net.Socket;

/**
 * class Manager - ядро сетевого файлового менеджера.
 */
public class Manager implements ManagerInterface {
    /**
     * Входной поток.
     */
    private DataInputStream dataInputStream;
    /**
     * Выходной поток.
     */
    private DataOutputStream dataOutputStream;
    /**
     * Корневой каталог.
     */
    private final File rootDir;
    /**
     * Текущий каталог.
     */
    private File currentDir;
    /**
     * String separator.
     */
    private final static String SEPARATOR = System.getProperty("line.separator");

    /**
     * Конструктор.
     * @param rootDir корневой каталог.
     * @param socket socket.
     */
    public Manager(File rootDir, Socket socket) {
        this.rootDir = rootDir;
        this.currentDir = rootDir;
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Показать содержимое каталога.
     */
    @Override
    public void show() {
        StringBuilder builder = new StringBuilder();
        File[] files = this.currentDir.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                builder.append(String.format("---DIR--- %s%s", file.getName(), SEPARATOR));
            } else {
                builder.append(String.format("--FILE-- %s%s", file.getName(), SEPARATOR));
            }
        }
        try {
            this.dataOutputStream.writeUTF(builder.toString());
            this.dataOutputStream.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Перейти в другой каталог.
     * @param dir название каталога.
     */
    @Override
    public void goToDir(String dir) {
        File directory = new File(this.currentDir, dir);
        if (directory.isDirectory()) {
            this.currentDir = directory;
            this.show();
        }
    }

    /**
     * Вернуться в корневой каталог.
     */
    @Override
    public void goToRoot() {
        this.currentDir = this.rootDir;
        this.show();
    }

    /**
     * Скачать файл.
     * @param fileName название файла.
     */
    @Override
    public void download(String fileName) {
        File file = new File(this.currentDir, fileName);
        int buffer;
        try (FileOutputStream stream = new FileOutputStream(file)) {
            long size = this.dataInputStream.readLong();
            System.out.println(String.format("Downloading file: %s | size: %d", file.getAbsolutePath(), size));
            long index = 0;
            while (size >= ++index) {
                buffer = this.dataInputStream.read();
                stream.write(buffer);
            }
            System.out.println("Download finished");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Загрузить файл.
     * @param fileName название файла.
     */
    @Override
    public void upload(String fileName) {
        File file = new File(this.currentDir, fileName);
        if (file.isFile()) {
            long size = file.length();
            System.out.println(String.format("Uploading file: %s | size: %d", file.getAbsolutePath(), size));
            try (FileInputStream stream = new FileInputStream(file)) {
                this.dataOutputStream.writeLong(size);
                int buffer = 0;
                while ((buffer = stream.read()) != -1) {
                    this.dataOutputStream.write(buffer);
                }
                System.out.println("Upload finished");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    /**
     * Unknown Command.
     */
    @Override
    public void unknownCommand() {
        System.out.println("Unknown command, please enter HELP for command list...");
    }

    /**
     * Отпавить сообщение.
     * @param message сообщение.
     */
    @Override
    public void sendMessage(String message) {
        try {
            this.dataOutputStream.writeUTF(message);
            this.dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Считать сообщение.
     */
    @Override
    public void readMessage() {
        try {
            String message = this.dataInputStream.readUTF();
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Выйти из программы и закрыть ресурсы.
     */
    @Override
    public void exit() {
        try {
            if (this.dataInputStream != null) {
                this.dataInputStream.close();
            }
            if (this.dataOutputStream != null) {
                this.dataOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
