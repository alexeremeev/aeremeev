package ru.job4j.tracker;

import java.util.Random;

/**
 * Class Tracker - система учета заявок.
 */
public class Tracker {
    /**
     * Массив заявок.
     */
    private Item[] items = new Item[100];
    /**
     * Начальный индекс массива для заполнения.
     */
    private int position = 0;
    /**
     * Константа Random RN для генерации уникальных id.
     */
    //CHECKSTYLE.OFF
    private final static Random RN = new Random();
    //CHECKSTYLE.ON
    /**
     * Метод добавления заявки в систему.
     * @param item заявка.
     * @return добавленная заявка.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
        return item;
    }

    /**
     * Метод обновления заявки в системе.
     * @param item заявка.
     */
    public void update(Item item) {
        for (int index = 0; index != position; index++) {
            if (items[index].getId().equals(item.getId())) {
                items[index] = item;
                break;
            }
        }
    }

    /**
     * Метод удаления заявки из системы.
     * @param item заявка.
     */
    public void delete(Item item) {
        for (int index = 0; index != position; index++) {
            if (items[index].getId().equals(item.getId())) {
                System.arraycopy(items, index + 1, items, index, position - index - 1);
                items[position] = null;
                position--;
            }
        }
    }

    /**
     * Метод поиска всех добавленных заявок.
     * @return массив добавленны заявок.
     */
    public Item[] findAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != position; index++) {
            result[index] = items[index];
        }
        return result;
    }

    /**
     * Метод поиска заявок по имени заявки.
     * @param name имя заявки.
     * @return массив всех заявок с указанным именем.
     */
    public Item[] findByName(String name) {
        Item[] swap = new Item[this.position];
        int index = 0;
        for (Item item : items) {
            if (item != null && item.getName().equals(name)) {
                swap[index] = item;
                index++;
            }
        }
        Item[] result = new Item[index];
        for (int i = 0; i != index; i++) {
            result[i] = swap[i];
        }
        return result;
    }

    /**
     * Метод поиска заявки по id.
     * @param id id заявки.
     * @return заявка.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Метод генерации уникального id.
     * @return id.
     */
    String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

}
