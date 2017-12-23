package ru.job4j.filemanager;

/**
 * interface ManagerInterface - интрефейс действий файлового менеджера.
 */
public interface ManagerInterface {
    /**
     * Показать содержимое каталога.
     */
    void show();

    /**
     * Перейти в другой каталог.
     * @param dir название каталога.
     */
    void goToDir(String dir);

    /**
     * Вернуться в корневой каталог.
     */
    void goToRoot();

    /**
     * Скачать файл.
     * @param fileName название файла.
     */
    void download(String fileName);

    /**
     * Загрузить файл.
     * @param fileName название файла.
     */
    void upload(String fileName);

    /**
     * Unknown Command.
     */
    void unknownCommand();

    /**
     * Отпавить сообщение.
     * @param message сообщение.
     */
    void sendMessage(String message);

    /**
     * Считать сообщение.
     */
    void readMessage();

    /**
     * Выйти из программы и закрыть ресурсы.
     */
    void exit();
}
