package ru.job4j.lsp.storage;

import org.junit.Test;
import ru.job4j.lsp.products.Bread;
import ru.job4j.lsp.products.Dairy;
import ru.job4j.lsp.products.Food;
import ru.job4j.lsp.products.Fruit;

import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Storage test.
 * @author aeremeev
 * @version 1
 * @since 09.01.2018
 */
public class StorageTest {
    /**
     * Test warehouse.
     */
    @Test
    public void whenAddMatchingFoodToWarehouseThenResultIsTrue() {
        Food orange = new Fruit("Orange", new GregorianCalendar(2018, GregorianCalendar.JANUARY, 1),
                new GregorianCalendar(2018, GregorianCalendar.JANUARY, 7), 120, 30);
        GregorianCalendar date = new GregorianCalendar(2018, GregorianCalendar.JANUARY, 1);

        Storage warehouse = new Warehouse("FoodCity", 10, date);

        boolean result = warehouse.match(orange);
        boolean expected = true;

        assertThat(result, is(expected));
    }

    /**
     * Test shop.
     */
    @Test
    public void whenAddMatchingFoodToShopThenResultIsTrue() {
        Food milk = new Dairy("Milk", new GregorianCalendar(2018, GregorianCalendar.JANUARY, 6),
                new GregorianCalendar(2018, GregorianCalendar.JANUARY, 13), 50, 10);

        GregorianCalendar date = new GregorianCalendar(2018, GregorianCalendar.JANUARY, 8);

        Storage shop = new Shop("Auchan", 10, date);

        boolean result = shop.match(milk);
        boolean expected = true;

        assertThat(result, is(expected));
    }

    /**
     * Test trash.
     */
    @Test
    public void whenAddMatchingFoodToTrashThenResultIsTrue() {
        Food bread = new Bread("Rye bread", new GregorianCalendar(2018, GregorianCalendar.JANUARY, 1),
                new GregorianCalendar(2018, GregorianCalendar.JANUARY, 3), 60, 5);

        GregorianCalendar date = new GregorianCalendar(2018, GregorianCalendar.JANUARY, 4);

        Storage trash = new Trash("Poligon", 1000, date);

        boolean result = trash.match(bread);
        boolean expected = true;

        assertThat(result, is(expected));
    }


}