package ru.job4j.tracker;

/**
 * Class StartUi - консольная оболочка класса Tracker.
 */
public class StartUi {
    /**
     * Метод ввода данных.
     */
    private Input input;
    /**
     * Трекер, как аргумент.
     */
    private Tracker tracker;
    /**
     * Константа выхода из программы.
     */
    //CHECKSTYLE.OFF
    private final int  EXIT = 6;
    //CHECKSTYLE.ON

    /**
     * Конструктор StartUi.
     * @param input интерфейс ввода.
     * @param tracker трекер.
     */
    public StartUi(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Инициализация меню.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        while (true) {
            menu.show();
            System.out.println("6. Exit Program");
            int key = Integer.valueOf(input.ask("Select: "));
            if (EXIT == key) {
                System.out.println("Goodbye!");
                break;
            }
            menu.select(key);
        }
    }

    /**
     * Точка входа.
     * @param args аргументы.
     */
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUi(input, tracker).init();
    }
}
