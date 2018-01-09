package ru.job4j.lsp.products;

import java.util.GregorianCalendar;
/**
 * Meat.
 * @author aeremeev
 * @since 08.01.2018
 * @version 1
 */
public class Meat extends Food {
    /**
     * Constructor.
     * @param name product name.
     * @param createDate product manufacture date.
     * @param expireDate product expire date.
     * @param price product price.
     * @param discount product applicable discount.
     */
    public Meat(String name, GregorianCalendar createDate, GregorianCalendar expireDate, int price, int discount) {
        super(name, createDate, expireDate, price, discount);
    }
}
