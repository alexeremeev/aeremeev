package ru.job4j.tracker;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Class Item - заявка в классе Tracker.
 */
public class Item {
    /**
     * Уникальный id заявки.
     */
    private int id;
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
    public int getId() {
        return this.id;
    }

    /**
     * Сеттер id заявки.
     * @param id заявки.
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Description: %s | Created: %s",
                this.id, this.name, this.description, new Timestamp(this.create));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Item item = (Item) o;

        if (getId() != item.getId()) {
            return false;
        }
        if (getCreate() != item.getCreate()) {
            return false;
        }
        if (getName() != null ? !getName().equals(item.getName()) : item.getName() != null) {
            return false;
        }
        return getDescription() != null ? getDescription().equals(item.getDescription()) : item.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (int) (getCreate() ^ (getCreate() >>> 32));
        return result;
    }
}
