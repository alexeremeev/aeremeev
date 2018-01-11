package ru.job4j.lsp.storage;

import ru.job4j.lsp.products.Food;
import ru.job4j.lsp.products.RecyclableFood;

import java.util.GregorianCalendar;

/**
 * Extended Warehouse.
 * @author aeremeev
 * @since 10.01.2018
 * @version 1
 */
public class WarehouseExtend extends StorageExtended {
    /**
     * Warehouse.
     */
    private Storage storage;

    /**
     * Constructor.
     * @param name storage name.
     * @param storageSize storage capacity.
     * @param today current date.
     * @param temperature temperature.
     * @param storage storage.
     */
    public WarehouseExtend(String name, int storageSize, GregorianCalendar today, int temperature, Storage storage) {
        super(name, storageSize, today, temperature);
        this.storage = storage;
    }

    @Override
    public void addFood(RecyclableFood recyclableFood) {
        this.addFood(recyclableFood.getFood());
    }

    @Override
    public void addFood(Food food) {
        if (!this.storage.checkStorageIsFull()) {
            this.storage.addFood(food);
        } else {
            super.addFood(food);
        }
    }

    @Override
    public boolean match(RecyclableFood recyclableFood) {
        return this.match(recyclableFood.getFood());
    }

    @Override
    public boolean match(Food food) {
        boolean result = false;
        if (this.storage.match(food)) {
            if (this.storage.checkStorageIsFull()) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index != storage.getStorageSpace().length; index++) {
            if (storage.getStorageSpace()[index] != null) {
                builder.append(storage.getStorageSpace()[index]);
            }
        }
        return super.toString() + builder.toString();
    }
}
