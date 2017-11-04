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
     * Конструктор.
     * @param database интерфейс для взаимодействия с БД.
     */
    public Tracker(Database database) {
        this.database = database;
    }
    /**
     * Метод добавления заявки в систему.
     * @param item заявка.
     * @return добавленная заявка.
     */
    public Item add(Item item) {
        String query = "INSERT INTO ITEMS (name, description, create_date) VALUES (?, ?, ?)";
        Object[] fields = new Object[] {item.getName(), item.getDescription(), new Timestamp(System.currentTimeMillis())};
        database.execute(query, fields);
        return item;
    }

    /**
     * Метод обновления заявки в системе.
     * @param item заявка.
     */
    public void update(Item item) {
        String query = "UPDATE ITEMS SET name = ?, description = ? where id = ?";
        Object[] fields = new Object[] {item.getName(), item.getDescription(), item.getId()};
        database.execute(query, fields);
    }

    /**
     * Метод удаления заявки из системы.
     * @param item заявка.
     */
    public void delete(Item item) {
        String query = "DELETE FROM ITEMS WHERE id = ?";
        Object[] fields = new Object[] {item.getId()};
        database.execute(query, fields);
    }

    /**
     * Метод поиска всех добавленных заявок.
     * @return список добавленны заявок.
     */
    public List<Item> findAll() {
        String query = "SELECT * FROM ITEMS WHERE id <> ?";
        Object[] fields = new Object[] {0};
        return database.getItems(query, fields);
    }

    /**
     * Метод поиска заявок по имени заявки.
     * @param name имя заявки.
     * @return список всех заявок с указанным именем.
     */
    public List<Item> findByName(String name) {
        String query = "SELECT * FROM ITEMS WHERE name = ?";
        Object[] fields = new Object[] {name};
        return database.getItems(query, fields);
    }

    /**
     * Метод поиска заявки по id.
     * @param id id заявки.
     * @return заявка.
     */
    public Item findById(int id) {
        String query = "SELECT * FROM ITEMS WHERE id = ?";
        Object[] fields = new Object[] {id};
        List<Item> list = database.getItems(query, fields);
        if (!list.isEmpty()) {
            return database.getItems(query, fields).get(0);
        } else {
            return null;
        }
    }
}
