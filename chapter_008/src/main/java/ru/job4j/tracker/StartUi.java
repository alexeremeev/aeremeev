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
    private Database database;
    /**
     * Загрузчик настроек.
     */
    private Settings settings;
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
     * @param settings settings.
     */
    public StartUi(Input input, Tracker tracker, Database database, Settings settings) {
        this.input = input;
        this.tracker = tracker;
        this.database = database;
        this.settings = settings;
    }
    /**
     * Инициализация меню.
     */
    public void init() {

        String url = this.settings.getSettings("DB_url");
        String username = this.settings.getSettings("DB_username");
        String password = this.settings.getSettings("DB_password");
        this.database.setConnection(url, username, password);
        if (database.select(this.settings.getSettings("SQL_CHECK_TABLE")) == 0) {
            database.execute(this.settings.getSettings("SQL_CREATE_TABLE"));
        }
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
        Settings settings = new Settings("tracker.properties");
        Tracker tracker = new Tracker(base, settings);
        new StartUi(input, tracker, base, settings).init();
    }
}
