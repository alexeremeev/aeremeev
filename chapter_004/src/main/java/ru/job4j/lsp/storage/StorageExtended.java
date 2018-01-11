package ru.job4j.lsp.storage;

import ru.job4j.lsp.products.RecyclableFood;

import java.util.GregorianCalendar;

/**
 * Storage - abstract class of extended product storage.
 * @author aeremeev
 * @since 09.01.2018
 * @version 1
 */
public abstract class StorageExtended extends Storage {
    /**
     * Recyclable products capacity.
     */
    private RecyclableFood[] recyclableFoods;
    /**
     * Storage temperature.
     */
    private int temperature;
    /**
     * Storage array index.
     */
    private int recyclePosition = 0;

    /**
     * Constructor.
     * @param name storage name.
     * @param storageSize storage capacity.
     * @param today current date.
     * @param temperature temperature.
     */
    public StorageExtended(String name, int storageSize, GregorianCalendar today, int temperature) {
        super(name, storageSize, today);
        this.recyclableFoods = new RecyclableFood[storageSize];
        this.temperature = temperature;
    }

    /**
     * Add recyclable food to storage.
     * @param recyclableFood recyclableFood.
     */
    public void addFood(RecyclableFood recyclableFood) {
        recyclableFoods[recyclePosition++] = recyclableFood;
    }

    /**
     * Temperature getter.
     * @return temperature.
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * Check food product by expiration date percentage for matching specific type of storage.
     * @param recyclableFood food product.
     * @return true, if it's matching.
     */
    public abstract boolean match(RecyclableFood recyclableFood);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s capacity: %d have following:", super.getName(), super.getStorageSpace().length));
        sb.append(System.getProperty("line.separator"));
        for (int index = 0; index != super.getStorageSpace().length; index++) {
            if (super.getStorageSpace()[index] != null) {
                sb.append(super.getStorageSpace()[index].toString());
            }
        }

        for (int index = 0; index != this.recyclableFoods.length; index++) {
            if (this.recyclableFoods[index] != null) {
                sb.append(this.recyclableFoods[index].toString());
            }
        }
        return sb.toString();
    }
}
