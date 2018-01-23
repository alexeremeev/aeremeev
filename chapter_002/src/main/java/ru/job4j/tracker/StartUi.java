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
    private final static int EXIT = 6;
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
        int[] ranges = new int[menu.getAction().size()];
        for (int index = 0; index != menu.getAction().size(); index++) {
            ranges[index] = menu.getAction().get(index).key();
        }
        while (true) {
            menu.show();
            int key = input.ask("Select: ", ranges);
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
        Input input = new ValidateInput();
        Tracker tracker = new Tracker();
        new StartUi(input, tracker).init();
    }
}
