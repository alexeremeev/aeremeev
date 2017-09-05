package ru.job4j.tracker;

/**
 * Class Item - заявка в классе Tracker.
 */
public class Item {
    /**
     * Уникальный id заявки.
     */
    private String id;
    /**
     * Имя заявки.
     */
    private String name;
    /**
     * Описание заявки.
     */
    private String description;
    /**
     * Время создания заявки (мс).
     */
    private long create;

    /**
     * Коструктор для Item.
     * @param name имя.
     * @param description описание.
     * @param create время создания.
     */
    public Item(String name, String description, long create) {
        this.name = name;
        this.description = description;
        this.create = create;
    }

    /**
     * Геттер имени.
     * @return имя заявки.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Геттер описания.
     * @return описание заявки.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Геттер времени создания.
     * @return время создания заявки.
     */
    public long getCreate() {
        return this.create;
    }

    /**
     * Геттер id заявки.
     * @return id заявки.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Сеттер id заявки.
     * @param id заявки.
     */
    public void setId(String id) {
        this.id = id;
    }
}
