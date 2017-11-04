package ru.job4j.tracker;

import ru.job4j.tracker.database.Database;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.ValidateInput;

/**
 * Class StartUi - консольная оболочка класса Tracker.
 */
public class StartUi {
    /**
     * Трекер.
     */
    private Tracker tracker;
    /**
     * Интерфейс для взаимодейстия в БД.
     */
    Database database;
    /**
     * Метод ввода данных.
     */
    private Input input;
    /**
     * Константа выхода из программы.
     */
    private static final int EXIT = 6;

    /**
     * Конструктор StartUi.
     * @param input метод ввода данных.
     * @param tracker трекер.
     * @param database БД.
     */
    public StartUi(Input input, Tracker tracker, Database database) {
        this.input = input;
        this.tracker = tracker;
        this.database = database;
    }
    /**
     * Инициализация меню.
     */
    public void init() {
        this.database.setConnection("settings.txt");
        this.database.createTable("create_tables.sql");
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        int[] ranges = new int[menu.getAction().size()];
        for (int index = 0; index != menu.getAction().size(); index++) {
            ranges[index] = menu.getAction().get(index).key();
        }
        try {
            System.out.println("Item Tracker");
            while (true) {
                menu.show();
                int key = input.ask("Select: ", ranges);
                if (EXIT == key) {
                    System.out.println("Goodbye!");
                    break;
                }
                menu.select(key);
            }
        } finally {
            this.database.endConnection();
        }

    }
    /**
     * Точка входа.
     * @param args аргументы.
     */
    public static void main(String[] args) {
        Input input = new ValidateInput();
        Database base = new Database();
        Tracker tracker = new Tracker(base);
        new StartUi(input, tracker, base).init();
    }
}
