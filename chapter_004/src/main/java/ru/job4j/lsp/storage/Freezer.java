package ru.job4j.lsp.storage;

import ru.job4j.lsp.products.Food;
import ru.job4j.lsp.products.RecyclableFood;

import java.util.GregorianCalendar;

/**
 * Freezer for recyclable garbage.
 * @author aeremeev
 * @since 10.01.2018
 * @version 1
 */
public class Freezer extends StorageExtended {
    /**
     * Internal trash.
     */
    private Trash trash;

    /**
     * Constructor.
     * @param name storage name.
     * @param storageSize storage capacity.
     * @param today current date.
     * @param temperature temperature.
     */
    public Freezer(String name, int storageSize, GregorianCalendar today, int temperature) {
        super(name, storageSize, today, temperature);
        this.trash = new Trash(name, storageSize, today);
    }

    /**
     * Match product with storage.
     * @param recyclableFood food product.
     * @return true, if matched.
     */
    public boolean match(RecyclableFood recyclableFood) {
        boolean result = false;

        if (recyclableFood.isRecyclable() && this.trash.match(recyclableFood.getFood()) && super.getTemperature() < 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean match(Food food) {
        return false;
    }
}
