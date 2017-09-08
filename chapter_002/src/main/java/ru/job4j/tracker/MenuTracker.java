package ru.job4j.tracker;

/**
 * class EditItem - внешний класс, редактирование заявки.
 */
class EditItem implements UserAction {
    /**
     * Реализация метода key интерфейса UserAction.
     * @return номер пункта меню.
     */
    public int key() {
        return 2;
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

    /**
     * Реализация метода info интерфейса UserAction.
     * @return описание пункта меню.
     */
    public String info() {
        return String.format("%s. %s", this.key(), "Edit item");
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
    private UserAction[] actions = new UserAction[6];

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
     * Заполнение массива пунктов меню.
     */
    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.ShowAllItems();
        this.actions[2] = new EditItem();
        this.actions[3] = this.new DeleteItem();
        this.actions[4] = this.new FindById();
        this.actions[5] = this.new FindByName();
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
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * class AddItem - внутренний класс, добавление заявки в трекер.
     */
    private class AddItem implements UserAction {
        /**
         * Реализация метода key интерфейса UserAction.
         * @return номер пункта меню.
         */
        public int key() {
            return 0;
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
        /**
         * Реализация метода info интерфейса UserAction.
         * @return описание пункта меню.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Add the new item");
        }
    }

    /**
     * class ShowAllItems - внутренний статический класс, вывод всех добавленных заявок.
     */
    private static class ShowAllItems implements UserAction {
        /**
         * Реализация метода key интерфейса UserAction.
         * @return номер пункта меню.
         */
        public int key() {
            return 1;
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
        /**
         * Реализация метода info интерфейса UserAction.
         * @return описание пункта меню.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }
    }

    /**
     * class DeleteItem - внутренний класс, удаление заявки.
     */
    private class DeleteItem implements UserAction {
        /**
         * Реализация метода key интерфейса UserAction.
         * @return номер пункта меню.
         */
        public int key() {
            return 3;
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
        /**
         * Реализация метода info интерфейса UserAction.
         * @return описание пункта меню.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item");
        }
    }

    /**
     * class FindById - внутренний класс, поиск заявки по ID.
     */
    private class FindById implements UserAction {
        /**
         * Реализация метода key интерфейса UserAction.
         * @return номер пункта меню.
         */
        public int key() {
            return 4;
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
        /**
         * Реализация метода info интерфейса UserAction.
         * @return описание пункта меню.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by Id");
        }
    }

    /**
     * class FindByName - внутренний класс, поиск всех заявок с одинаковым именем.
     */
    private class FindByName implements UserAction {
        /**
         * Реализация метода key интерфейса UserAction.
         * @return номер пункта меню.
         */
        public int key() {
            return 5;
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
        /**
         * Реализация метода info интерфейса UserAction.
         * @return описание пункта меню.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find items by name");
        }
    }
}
