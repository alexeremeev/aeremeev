package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Class Tracker - система учета заявок.
 */
public class Tracker {
    /**
     * Item List.
     */
    private List<Item> items = new ArrayList<>();
    /**
     * Константа Random RN для генерации уникальных id.
     */
    private final static Random RN = new Random();
    /**
     * Метод добавления заявки в систему.
     * @param item заявка.
     * @return добавленная заявка.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод обновления заявки в системе.
     * @param item заявка.
     */
    public void update(Item item) {
        this.items.replaceAll(swap -> swap.getId().equals(item.getId()) ? item : swap);
    }

    /**
     * Метод удаления заявки из системы.
     * @param item заявка.
     */
    public void delete(Item item) {
        this.items.removeIf(search -> search.getId().equals(item.getId()));
    }

    /**
     * Метод поиска всех добавленных заявок.
     * @return массив добавленны заявок.
     */
    public List<Item> findAll() {
        return this.items.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * Метод поиска заявок по имени заявки.
     * @param name имя заявки.
     * @return массив всех заявок с указанным именем.
     */
    public List<Item> findByName(String name) {
        return this.items.stream().filter(item -> item.getName().equals(name)).collect(Collectors.toList());
    }

    /**
     * Метод поиска заявки по id.
     * @param id id заявки.
     * @return заявка.
     */
    public Item findById(String id) {
        return this.items.stream().filter(item -> item.getId().equals(id)).findFirst().get();
    }

    /**
     * Метод генерации уникального id.
     * @return id.
     */
    String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

}
