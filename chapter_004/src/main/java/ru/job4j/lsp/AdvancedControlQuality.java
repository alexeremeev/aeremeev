package ru.job4j.lsp;

import ru.job4j.lsp.products.RecyclableFood;
import ru.job4j.lsp.storage.Storage;
import ru.job4j.lsp.storage.StorageExtended;

import java.util.List;

/**
 * Advanced Control quality. Sort and match products to storage types by expiration date.
 * @author aeremeev
 * @since 10.01.2018
 * @version 1
 */
public class AdvancedControlQuality extends ControlQuality {
    /**
     * List of available storages.
     */
    private List<StorageExtended> extendedList;

    /**
     * Constructor.
     * @param storageList basic storages list.
     * @param extendedList extended storages list.
     */
    public AdvancedControlQuality(List<Storage> storageList, List<StorageExtended> extendedList) {
        super(storageList);
        this.extendedList = extendedList;
    }

    /**
     * Add recyclable food to storage.
     * @param recyclableFood
     */
    public void addFood(RecyclableFood recyclableFood) {
        boolean recycle = false;
        for (StorageExtended storage: extendedList) {
            if (storage.match(recyclableFood)) {
                storage.addFood(recyclableFood);
                recycle = true;
                break;
            }
        }
        if (!recycle) {
            super.sort(recyclableFood.getFood());
        }
    }

    /**
     * Add extended storage.
     * @param storageExtended extended storage.
     */
    public void addExtStorage(StorageExtended storageExtended) {
        this.extendedList.add(storageExtended);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (StorageExtended storageExtended: extendedList) {
            builder.append(storageExtended);
        }
        return super.toString() + builder.toString();
    }
}
