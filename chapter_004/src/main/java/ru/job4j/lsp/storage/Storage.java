package ru.job4j.lsp.storage;

import ru.job4j.lsp.products.Food;

import java.util.GregorianCalendar;

/**
 * Storage - abstract class of product storage.
 * @author aeremeev
 * @since 08.01.2018
 * @version 1
 */
public abstract class Storage {
    /**
     * Storage name.
     */
    private String name;
    /**
     * Storage capacity.
     */
    private Food[] storageSpace;
    /**
     * Storage array index.
     */
    private int position = 0;
    /**
     * Current date.
     */
    private GregorianCalendar today;

    /**
     * Constructor.
     * @param name storage name.
     * @param storageSize storage capacity.
     * @param today current date.
     */
    public Storage(String name, int storageSize, GregorianCalendar today) {
        this.name = name;
        this.storageSpace = new Food[storageSize];
        this.today = today;
    }

    /**
     * Date getter.
     * @return date.
     */
    public GregorianCalendar getToday() {
        return today;
    }

    /**
     * Add food product to storage.
     * @param food food product.
     */
    public void addFood(Food food) {
        this.storageSpace[position++] = food;
    }

    /**
     * Check if storage is full.
     * @return true, if storage is full.
     */
    public boolean checkStorageIsFull() {
        boolean result = false;
        if (this.position >= this.storageSpace.length) {
            result = true;
        }
        return result;
    }

    /**
     * Check food product by expiration date percentage for matching specific type of storage.
     * @param food food product.
     * @return true, if it's matching.
     */
    public abstract boolean match(Food food);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s capacity: %d have following:", this.name, this.storageSpace.length));
        sb.append(System.getProperty("line.separator"));
        for (int index = 0; index != this.storageSpace.length; index++) {
            if (this.storageSpace[index] != null) {
                sb.append(this.storageSpace[index].toString());
            }
        }
        return sb.toString();
    }
}
