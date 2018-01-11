package ru.job4j.lsp.products;

/**
 * RecyclableFruit.
 * @author aeremeev
 * @since 09.01.2018
 * @version 1
 */
public class RecyclableFruit extends RecyclableFood {

    /**
     * Constructor.
     * @param food food.
     * @param recyclable recyclable flag.
     */
    public RecyclableFruit(Food food, boolean recyclable) {
        super(food, recyclable);
    }
}
