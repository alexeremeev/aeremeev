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
     * Константы выбора меню.
     */
    //CHECKSTYLE.OFF
    private final static String ADD = "0";
    private final static String SHOW = "1";
    private final static String EDIT = "2";
    private final static String DELETE = "3";
    private final static String FINDBYID = "4";
    private final static String FINDBYNAME = "5";
    private final static String EXIT = "6";
    //CHECKSTYLE.OFF
    /**
     * Строка сепаратор.
     */
    private final static String line = System.getProperty("line.separator");
    /**
     * Строка вывода меню.
     */
    private final static String SHOWMENU = new StringBuilder()
            .append("Tracker Menu:").append(line)
            .append("0. Add new Item").append(line)
            .append("1. Show all items").append(line)
            .append("2. Edit item").append(line)
            .append("3. Delete item").append(line)
            .append("4. Find item by Id").append(line)
            .append("5. Find items by name").append(line)
            .append("6. Exit Program").append(line).toString();

    public StartUi(Input input) {
        this.input = input;
    }

    public void init() {
        Tracker tracker = new Tracker();
        while (true) {
            System.out.println(SHOWMENU);
            String choice = input.ask("Select:");
            if (ADD.equals(choice)) {
                String name = input.ask("Please, enter item name: ");
                String description = input.ask("Please, enter item description: ");
                tracker.add(new Item(name, description, System.currentTimeMillis()));
                System.out.println("Your item added to tracker");
            } else if (SHOW.equals(choice)) {
                for (Item item : tracker.findAll()) {
                    System.out.println(item.toString());
                }
            } else if (EDIT.equals(choice)) {
                String id = input.ask("Enter item ID: ");
                System.out.println(tracker.findById(id).toString());
                String name = input.ask("Enter new name: ");
                String description = input.ask("Enter new description: ");
                Item swap = new Item(name, description, System.currentTimeMillis());
                swap.setId(id);
                tracker.update(swap);
                System.out.println(tracker.findById(id));
            } else if (DELETE.equals(choice)) {
                String delete = input.ask("Delete item. Please, enter ID: ");
                tracker.delete(tracker.findById(delete));
                System.out.println("Item ID: " + delete + " deleted from tracker.");
            } else if (FINDBYID.equals(choice)) {
                String id = input.ask("Enter item ID: ");
                System.out.println(tracker.findById(id).toString());
            } else if (FINDBYNAME.equals(choice)) {
                String name = input.ask("Enter item name: ");
                for (Item item : tracker.findByName(name)) {
                    System.out.println(item.toString());
                }
            } else if (EXIT.equals(choice)) {
                System.out.println("Goodbye!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        new StartUi(input).init();
    }
}
