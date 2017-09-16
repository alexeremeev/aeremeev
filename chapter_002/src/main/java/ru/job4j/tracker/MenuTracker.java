package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * class EditItem - внешний класс, редактирование заявки.
 */
class EditItem extends BaseAction {
    /**
     * Конструктор, реализующий конструктор BaseAction.
     * @param key номер пункта меню.
     * @param name название пункта меню.
     */
    EditItem(int key, String name) {
        super(key, name);
    }
    /**
     * Реализация метода execute интерфейса UserAction.
     * @param input интерфейс ввода.
     * @param tracker трекер.
     */
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Enter item ID: ");
        System.out.println(tracker.findById(id).toString());
        String name = input.ask("Enter new name: ");
        String description = input.ask("Enter new description: ");
        Item swap = new Item(name, description, System.currentTimeMillis());
        swap.setId(id);
        tracker.update(swap);
        System.out.println(tracker.findById(id));
    }
}

/**
 * Class MenuTracker - меню трекера заявок.
 */
public class MenuTracker {
    /**
     * Интерфейс ввода данных в трекер.
     */
    private Input input;
    /**
     * Трекер.
     */
    private Tracker tracker;
    /**
     * Массив пунктов меню.
     */
    private ArrayList<UserAction> actions = new ArrayList<>();
    //private UserAction[] actions = new UserAction[7];
    /**
     * Счетчик элементов массива actions.
     */
    private int position = 0;

    /**
     * Конструктор MenuTracker.
     * @param input интерфейс ввода данных в трекер.
     * @param tracker трекер.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Запрос массива действий, доспутных пользователю.
     * @return массив действий, доспутных пользователю.
     */
    public ArrayList<UserAction> getAction() {
        return this.actions;
    }

    /**
     * Заполнение массива пунктов меню.
     */
    public void fillActions() {
        this.actions.add(position++, this.new AddItem(0, "Add the new item"));
        this.actions.add(position++, new MenuTracker.ShowAllItems(1, "Show all items"));
        this.actions.add(position++, new EditItem(2, "Edit Item"));
        this.actions.add(position++, this.new DeleteItem(3, "Delete item"));
        this.actions.add(position++, this.new FindById(4, "Find item by Id"));
        this.actions.add(position++, this.new FindByName(5, "Find items by name"));
        this.actions.add(position++, this.new Exit(6, "Exit program"));
    }

    /**
     * Вывод пунктов меню в консоль.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Выбор и исполнение пункта меню.
     * @param key номер пункта меню.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * class AddItem - внутренний класс, добавление заявки в трекер.
     */
    private class AddItem extends BaseAction {
        /**
         * Конструктор, реализующий конструктор BaseAction.
         * @param key номер пункта меню.
         * @param name название пункта меню.
         */
        AddItem(int key, String name) {
            super(key, name);
        }
        /**
         * Реализация метода execute интерфейса UserAction.
         * @param input интерфейс ввода.
         * @param tracker трекер.
         */
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please, enter item name: ");
            String description = input.ask("Please, enter item description: ");
            tracker.add(new Item(name, description, System.currentTimeMillis()));
            System.out.println("Your item added to tracker");
        }
    }

    /**
     * class ShowAllItems - внутренний статический класс, вывод всех добавленных заявок.
     */
    private static class ShowAllItems extends BaseAction {
        /**
         * Конструктор, реализующий конструктор BaseAction.
         * @param key номер пункта меню.
         * @param name название пункта меню.
         */
        ShowAllItems(int key, String name) {
            super(key, name);
        }

        /**
         * Реализация метода execute интерфейса UserAction.
         * @param input интерфейс ввода.
         * @param tracker трекер.
         */
        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.findAll()) {
                System.out.println(item.toString());
            }
        }
    }

    /**
     * class DeleteItem - внутренний класс, удаление заявки.
     */
    private class DeleteItem extends BaseAction {
        /**
         * Конструктор, реализующий конструктор BaseAction.
         * @param key номер пункта меню.
         * @param name название пункта меню.
         */
        DeleteItem(int key, String name) {
            super(key, name);
        }
        /**
         * Реализация метода execute интерфейса UserAction.
         * @param input интерфейс ввода.
         * @param tracker трекер.
         */
        public void execute(Input input, Tracker tracker) {
            String delete = input.ask("Delete item. Please, enter ID: ");
            tracker.delete(tracker.findById(delete));
            System.out.println("Item ID: " + delete + " deleted from tracker.");
        }
    }

    /**
     * class FindById - внутренний класс, поиск заявки по ID.
     */
    private class FindById extends BaseAction {
        /**
         * Конструктор, реализующий конструктор BaseAction.
         * @param key номер пункта меню.
         * @param name название пункта меню.
         */
        FindById(int key, String name) {
            super(key, name);
        }
        /**
         * Реализация метода execute интерфейса UserAction.
         * @param input интерфейс ввода.
         * @param tracker трекер.
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter item ID: ");
            System.out.println(tracker.findById(id).toString());
        }
    }

    /**
     * class FindByName - внутренний класс, поиск всех заявок с одинаковым именем.
     */
    private class FindByName extends BaseAction {
        /**
         * Конструктор, реализующий конструктор BaseAction.
         * @param key номер пункта меню.
         * @param name название пункта меню.
         */
        FindByName(int key, String name) {
            super(key, name);
        }
        /**
         * Реализация метода execute интерфейса UserAction.
         * @param input интерфейс ввода.
         * @param tracker трекер.
         */
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter item name: ");
            for (Item item : tracker.findByName(name)) {
                System.out.println(item.toString());
            }
        }
    }

    /**
     * Выход из меню.
     */
    private class Exit extends BaseAction {
        /**
         * Конструктор, реализующий конструктор BaseAction.
         * @param key номер пункта меню.
         * @param name название пункта меню.
         */
        Exit(int key, String name) {
            super(key, name);
        }
        /**
         * Реализация метода execute интерфейса UserAction.
         * @param input интерфейс ввода.
         * @param tracker трекер.
         */
        public void execute(Input input, Tracker tracker) {
        }
    }
}
