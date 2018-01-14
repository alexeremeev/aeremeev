package ru.job4j.lsp;

import ru.job4j.lsp.products.Food;
import ru.job4j.lsp.storage.Storage;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Control quality. Sort and match products to storage types by expiration date.
 * @author aeremeev
 * @since 09.01.2018
 * @version 1
 */
public class ControlQuality {
    /**
     * List of available storage options.
     */
    private List<Storage> storageList;

    /**
     * Constructor.
     * @param storageList list of available storage options.
     */
    public ControlQuality(List<Storage> storageList) {
        this.storageList = storageList;
    }

    /**
     * Add storage to control quality.
     * @param storage storage.
     */
    public void addStorage(Storage storage) {
        this.storageList.add(storage);
    }

    /**
     * Sort food products to storage types by expiration date.
     * @param food food product.
     */
    public void sort(Food food) {
        for (Storage storage: this.storageList) {
            if (storage.match(food)) {
                storage.addFood(food);
                break;
            }
        }
    }

    /**
     * Resorts food by new current date.
     * @param today new current date.
     */
    public void resort(GregorianCalendar today) {
        List<Food> foods = new ArrayList<>();
        for (Storage storage: this.storageList) {
            storage.setToday(today);
            storage.resetPosition();
            for (int index = 0; index != storage.getStorageSpace().length; index++) {
                if (storage.getStorageSpace()[index] != null) {
                    foods.add(storage.getStorageSpace()[index]);
                    storage.getStorageSpace()[index] = null;
                }
            }
        }
        for (Food food: foods) {
            this.sort(food);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Storage storage: this.storageList) {
            sb.append(storage);
        }
        return sb.toString();
    }

}
