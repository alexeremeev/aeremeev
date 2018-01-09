package ru.job4j.lsp.storage;

import ru.job4j.lsp.products.Food;

import java.util.GregorianCalendar;
/**
 * Warehouse.
 * @author aeremeev
 * @since 08.01.2018
 * @version 1
 */
public class Warehouse extends Storage {
     /**
     * Constructor.
     * @param name warehouse name.
     * @param storageSize warehouse capacity.
     * @param today current date.
     */
    public Warehouse(String name, int storageSize, GregorianCalendar today) {
        super(name, storageSize, today);
    }

    @Override
    public boolean match(Food food) {
        boolean result = false;
        if (food.getPercentOfBestBefore(this.getToday()) < 25 && !this.checkStorageIsFull()) {
            result = true;
        }
        return result;
    }
}
