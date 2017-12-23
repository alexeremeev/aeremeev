package ru.job4j.filemanager;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    /**
     * Флаг для обработки меню со стороны сервера.
     */
    private final boolean serverSide;
    /**
     * Входной поток сервера.
     */
    private DataInputStream dataInputStream;
    /**
     * Входной поток клиента.
     */
    private Scanner consoleInput;
    /**
     * Actions map.
     */
    private Map<String, BasicAction> actions = new LinkedHashMap<>();
    /**
     * Длина стандартной команды.
     */
    private final static int COMMAND_SIZE = 4;

    /**
     * Заполнить карту всеми возможными действиями.
     */
    private void fillActions() {
        this.actions.put("/CD ", new GoToDirectory("Go to directory, usage example: /CD Dir"));
        this.actions.put("DWNL", new DownloadFile("Download file, usage example: DWNL Filename.ext"));
        this.actions.put("EXIT", new ExitProgram("Exit program"));
        this.actions.put("HELP", new Help("Help, will print this message"));
        this.actions.put("HOME", new GoToRootDirectory("Go to root directory"));
        this.actions.put("SHOW", new ShowFiles("Show files & folders in current directory"));
        this.actions.put("UPLD", new UploadFile("Upload file, usage example: UPLD Filename.ext"));
    }
    /**
     * Вывести список файлов и папок текущей директории.
     */
    private class ShowFiles extends BasicAction {

        private ShowFiles(String description) {
            super(description);
        }
        public void execute(Manager manager, String select) {
            if (serverSide) {
                manager.show();
            } else {
                manager.sendMessage(select);
                manager.readMessage();
            }

        }
    }
    /**
     * Перейти в папку.
     */
    private class GoToDirectory extends BasicAction {

        private GoToDirectory(String description) {
            super(description);
        }

        @Override
        public void execute(Manager manager, String select) {
            if (serverSide) {
                manager.goToDir(select.substring(COMMAND_SIZE));
            } else {
                manager.sendMessage(select);
                manager.readMessage();
            }
        }
    }

    /**
     * Вернуться в корневой каталог.
     */
    private class GoToRootDirectory extends BasicAction {

        private GoToRootDirectory(String description) {
            super(description);
        }

        @Override
        public void execute(Manager manager, String select) {
            if (serverSide) {
                manager.goToRoot();
            } else {
                manager.sendMessage(select);
                manager.readMessage();
            }
        }
    }
    /**
     * Скачать файл.
     */
    private class DownloadFile extends BasicAction {

        private DownloadFile(String description) {
            super(description);
        }

        @Override
        public void execute(Manager manager, String select) {
            String fileName = select.substring(COMMAND_SIZE + 1);
            if (serverSide) {
                manager.upload(fileName);
            } else {
                manager.sendMessage(select);
                manager.download(fileName);
            }
        }
    }

    /**
     * Загрузить файл.
     */
    private class UploadFile extends BasicAction {

        private UploadFile(String description) {
            super(description);
        }

        @Override
        public void execute(Manager manager, String select) {
            String fileName = select.substring(COMMAND_SIZE + 1);
            if (serverSide) {
                manager.download(fileName);
            } else {
                manager.sendMessage(select);
                manager.upload(fileName);
            }
        }
    }

    /**
     * Выйти из программы.
     */
    private class ExitProgram extends BasicAction {

        public ExitProgram(String description) {
            super(description);
        }

        @Override
        public void execute(Manager manager, String select) {
            if (serverSide) {
                manager.exit();
            } else {
                manager.sendMessage(select);
                manager.readMessage();
            }
        }
    }

    /**
     * Вывести справку.
     */
    private class Help extends BasicAction {
        public Help(String description) {
            super(description);
        }

        @Override
        public void execute(Manager manager, String select) {
            StringBuilder builder = new StringBuilder();
            if (serverSide) {
                for (Map.Entry<String, BasicAction> entry : actions.entrySet()) {
                    builder.append(String.format("%s %s %s", entry.getKey(), entry.getValue().info(), System.getProperty("line.separator")));
            }
            manager.sendMessage(builder.toString());

            } else {
                manager.sendMessage(select);
                manager.readMessage();
            }
        }
    }
    /**
     * Конструктор для серверной стороны.
     * @param socket socket.
     * @throws IOException IOException.
     */
    public Menu(Socket socket) throws IOException {
        this.serverSide = true;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
    }

    /**
     * Конструктор для клиентской стороны.
     * @throws IOException IOException.
     */
    public Menu() throws IOException {
        this.serverSide = false;
        this.consoleInput = new Scanner(System.in);
    }

    /**
     * Инициализция меню.
     * @param manager manager.
     */
    public void init(Manager manager) {
        String select = null;
        this.fillActions();
        try {
            do {
                if (this.serverSide) {
                    select = this.dataInputStream.readUTF();
                } else {
                    select = this.consoleInput.nextLine();
                }
                if (select.length() >= COMMAND_SIZE) {
                    if (actions.containsKey(select.substring(0, COMMAND_SIZE))) {
                        this.actions.get(select.substring(0, COMMAND_SIZE)).execute(manager, select);
                    }
                } else {
                    manager.unknownCommand();
                }
            } while (!select.equalsIgnoreCase("EXIT"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
