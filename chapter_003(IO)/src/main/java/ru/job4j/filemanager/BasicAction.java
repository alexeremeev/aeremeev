package ru.job4j.filemanager;

/**
 * abstract class BasicAction - базовое действие в меню.
 */
public abstract class BasicAction implements UserAction {
    /**
     * Описание пункта.
     */
    private String description;

    /**
     * Конструктор.
     * @param description описание пункта.
     */
    public BasicAction(String description) {
        this.description = description;
    }

    /**
     * Описание пункта меню.
     * @return описание пункта меню.
     */
    public String info() {
        return this.description;
    }
}
