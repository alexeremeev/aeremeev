package ru.job4j.lsp.storage;

import ru.job4j.lsp.products.Food;

import java.util.GregorianCalendar;

/**
 * Shop.
 * @author aeremeev
 * @since 08.01.2018
 * @version 1
 */
public class Shop extends Storage {
    /**
     * Constructor.
     * @param name shop name.
     * @param storageSize shop capacity.
     * @param today current date.
     */
    public Shop(String name, int storageSize, GregorianCalendar today) {
        super(name, storageSize, today);
    }

    @Override
    public void addFood(Food food) {
        int bestBeforePercent = food.getPercentOfBestBefore(this.getToday());
        if (bestBeforePercent > 75 && bestBeforePercent <= 100) {
            food.applyDiscount();
        }
        super.addFood(food);
    }

    @Override
    public boolean match(Food food) {
        boolean result = false;
        int bestBeforePercent = food.getPercentOfBestBefore(this.getToday());
        if (bestBeforePercent >= 25 && bestBeforePercent <= 100 && !this.checkStorageIsFull()) {
            result = true;
        }
        return result;
    }
}
