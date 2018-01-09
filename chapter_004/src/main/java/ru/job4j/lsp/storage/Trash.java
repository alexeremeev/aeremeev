package ru.job4j.lsp.storage;

import ru.job4j.lsp.products.Food;

import java.util.GregorianCalendar;
/**
 * Trash
 * @author aeremeev
 * @since 08.01.2018
 * @version 1
 */
public class Trash extends Storage {
    /**
     * Constructor.
     * @param name trash name.
     * @param storageSize trash capacity.
     * @param today current date.
     */
    public Trash(String name, int storageSize, GregorianCalendar today) {
        super(name, storageSize, today);
    }

    @Override
    public boolean match(Food food) {
        boolean result = false;
        if (food.getPercentOfBestBefore(this.getToday()) > 100 && !this.checkStorageIsFull()) {
            result = true;
        }
        return result;
    }
}
