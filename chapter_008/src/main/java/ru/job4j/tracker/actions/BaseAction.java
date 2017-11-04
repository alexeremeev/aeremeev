package ru.job4j.tracker.actions;

/**
 * abstract class BaseAction - реализует общие методы для действие пользователя интерфейса UserAction.
 */
public abstract class BaseAction implements UserAction {
    /**
     * Номер пункта меню.
     */
    private int key;
    /**
     * Название пункта меню.
     */
    private String name;

    /**
     * Конструктор.
     * @param key номер пункта меню.
     * @param name название пункта меню.
     */
    public BaseAction(int key, String name) {
         this.key = key;
         this.name = name;
        }

    /**
     * Ключ меню выбора.
     * @return номер меню.
     */
    public int key() {
        return this.key;
    }

    /**
     * Описание пункта меню.
     * @return описание пункта меню.
     */
    public String info() {
      return String.format("%s. %s", this.key, this.name);
    }
}
