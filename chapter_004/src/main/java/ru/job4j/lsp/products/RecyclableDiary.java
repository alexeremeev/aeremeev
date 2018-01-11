package ru.job4j.lsp.products;

/**
 * RecyclableDairy.
 * @author aeremeev
 * @since 09.01.2018
 * @version 1
 */
public class RecyclableDiary extends RecyclableFood {

    /**
     * Constructor.
     * @param food food.
     * @param recyclable recyclable flag.
     */
    public RecyclableDiary(Food food, boolean recyclable) {
        super(food, recyclable);
    }
}
