package ru.job4j.tracker;

import ru.job4j.tracker.database.Database;

import java.sql.Timestamp;
import java.util.List;


/**
 * Class Tracker - система учета заявок.
 */
public class Tracker {
    /**
     * Интерфейс для взаимодействия с БД.
     */
    private Database database;
    /**
     * Настройки settings.
     */
    private Settings settings;

    /**
     * Конструктор.
     * @param database интерфейс для взаимодействия с БД.
     */
    public Tracker(Database database, Settings settings) {
        this.database = database;
        this.settings = settings;
    }
    /**
     * Метод добавления заявки в систему.
     * @param item заявка.
     * @return добавленная заявка.
     */
    public Item add(Item item) {
        String query = this.settings.getSettings("SQL_INSERT");
        Object[] fields = new Object[] {item.getName(), item.getDescription(), new Timestamp(System.currentTimeMillis())};
        database.executeWithArgs(query, fields);
        return item;
    }

    /**
     * Метод обновления заявки в системе.
     * @param item заявка.
     */
    public void update(Item item) {
        String query = this.settings.getSettings("SQL_UPDATE");
        Object[] fields = new Object[] {item.getName(), item.getDescription(), item.getId()};
        database.executeWithArgs(query, fields);
    }

    /**
     * Метод удаления заявки из системы.
     * @param item заявка.
     */
    public void delete(Item item) {
        String query = this.settings.getSettings("SQL_DELETE");
        Object[] fields = new Object[] {item.getId()};
        database.executeWithArgs(query, fields);
    }

    /**
     * Метод поиска всех добавленных заявок.
     * @return список добавленны заявок.
     */
    public List<Item> findAll() {
        String query = this.settings.getSettings("SQL_FIND_ALL");
        Object[] fields = new Object[] {0};
        return database.getItems(query, fields);
    }

    /**
     * Метод поиска заявок по имени заявки.
     * @param name имя заявки.
     * @return список всех заявок с указанным именем.
     */
    public List<Item> findByName(String name) {
        String query = this.settings.getSettings("SQL_FIND_BY_NAME");
        Object[] fields = new Object[] {name};
        return database.getItems(query, fields);
    }

    /**
     * Метод поиска заявки по id.
     * @param id id заявки.
     * @return заявка.
     */
    public Item findById(int id) {
        String query = this.settings.getSettings("SQL_FIND_BY_ID");
        Object[] fields = new Object[] {id};
        List<Item> list = database.getItems(query, fields);
        if (!list.isEmpty()) {
            return database.getItems(query, fields).get(0);
        } else {
            return null;
        }
    }
}
