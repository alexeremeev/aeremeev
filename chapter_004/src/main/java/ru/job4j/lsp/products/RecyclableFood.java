package ru.job4j.lsp.products;

import java.util.GregorianCalendar;

/**
 * RecyclableFood. Can be recycled after expiration date.
 * @author aeremeev
 * @since 09.01.2018
 * @version 1
 */
public class RecyclableFood {
    /**
     * Food.
     */
    private Food food;
    /**
     * Recyclable flag.
     */
    private boolean recyclable;

    /**
     * Constructor.
     * @param food food.
     * @param recyclable recyclable flag.
     */
    public RecyclableFood(Food food, boolean recyclable) {
        this.food = food;
        this.recyclable = recyclable;

    }

    /**
     * Food getter.
     * @return food.
     */
    public Food getFood() {
        return food;
    }

    /**
     * Recyclable getter.
     * @return recyclable flag.
     */
    public boolean isRecyclable() {
        return this.recyclable;
    }

    /**
     * Get percentage of product expiration date. 0 - fresh, >100 - garbage.
     * @param today current date.
     * @return percentage of product expiration date.
     */
    public int getPercentOfBestBefore(GregorianCalendar today) {
        return this.food.getPercentOfBestBefore(today);
    }

    @Override
    public String toString() {
        return food.toString();
    }
}
