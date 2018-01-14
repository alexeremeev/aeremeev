package ru.job4j.lsp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.lsp.products.*;
import ru.job4j.lsp.storage.Shop;
import ru.job4j.lsp.storage.Trash;
import ru.job4j.lsp.storage.Warehouse;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Control Quality test.
 * @author aeremeev
 * @version 1
 * @since 09.01.2018
 */
public class ControlQualityTest {

    private final String separator = System.getProperty("line.separator");

    private List<Food> products = new ArrayList<>();

    /**
     * Fill product list.
     */
    @Before
    public void fillProducts() {

        products.add(new Fruit("Orange", new GregorianCalendar(2018, GregorianCalendar.JANUARY, 1),
                new GregorianCalendar(2018, GregorianCalendar.JANUARY, 7), 120, 30));

        products.add(new Dairy("Milk", new GregorianCalendar(2018, GregorianCalendar.JANUARY, 6),
                new GregorianCalendar(2018, GregorianCalendar.JANUARY, 13), 50, 10));

        products.add(new Bread("Rye bread", new GregorianCalendar(2018, GregorianCalendar.JANUARY, 1),
                new GregorianCalendar(2018, GregorianCalendar.JANUARY, 3), 60, 5));

        products.add(new Meat("Beef", new GregorianCalendar(2018, GregorianCalendar.JANUARY, 1),
                new GregorianCalendar(2018, GregorianCalendar.JANUARY, 5), 500, 100));

        products.add(new Meat("Bologna sausage", new GregorianCalendar(2017, GregorianCalendar.DECEMBER, 21),
                new GregorianCalendar(2018, GregorianCalendar.JANUARY, 7), 600, 100));

        products.add(new Dairy("Sour Cream", new GregorianCalendar(2018, GregorianCalendar.JANUARY, 1),
                new GregorianCalendar(2018, GregorianCalendar.JANUARY, 7), 80, 10));

        products.add(new Fruit("Apple", new GregorianCalendar(2018, GregorianCalendar.JANUARY, 1),
                new GregorianCalendar(2018, GregorianCalendar.JANUARY, 30), 70, 5));

        products.add(new Fruit("Banana", new GregorianCalendar(2018, GregorianCalendar.JANUARY, 1),
                new GregorianCalendar(2018, GregorianCalendar.JANUARY, 5), 100, 5));

        products.add(new Bread("White Bread", new GregorianCalendar(2018, GregorianCalendar.JANUARY, 1),
                new GregorianCalendar(2018, GregorianCalendar.JANUARY, 3), 50, 3));


    }

    /**
     * Test control quality by checking correct sort of products list.
     */
    @Test
    public void whenOfferProductsToControlQualityThenGetCorrectSort() {
        GregorianCalendar date = new GregorianCalendar(2018, GregorianCalendar.JANUARY, 6);
        ControlQuality cq = new ControlQuality(new ArrayList<>());

        cq.addStorage(new Shop("Crossroad", 10, date));
        cq.addStorage(new Warehouse("FoodCity", 100, date));
        cq.addStorage(new Trash("Poligon", 1000, date));

        for (Food food: products) {
            cq.sort(food);
        }

        StringBuilder expected = new StringBuilder();
        expected.append("Crossroad capacity: 10 have following:");
        expected.append(separator);
        expected.append("{name = 'Orange', createDate = 1 1 2018, expireDate = 7 1 2018, price = 90, discount = 30}");
        expected.append(separator);
        expected.append("{name = 'Bologna sausage', createDate = 21 12 2017, expireDate = 7 1 2018, price = 500, discount = 100}");
        expected.append(separator);
        expected.append("{name = 'Sour Cream', createDate = 1 1 2018, expireDate = 7 1 2018, price = 70, discount = 10}");
        expected.append(separator);
        expected.append("FoodCity capacity: 100 have following:");
        expected.append(separator);
        expected.append("{name = 'Milk', createDate = 6 1 2018, expireDate = 13 1 2018, price = 50, discount = 10}");
        expected.append(separator);
        expected.append("{name = 'Apple', createDate = 1 1 2018, expireDate = 30 1 2018, price = 70, discount = 5}");
        expected.append(separator);
        expected.append("Poligon capacity: 1000 have following:");
        expected.append(separator);
        expected.append("{name = 'Rye bread', createDate = 1 1 2018, expireDate = 3 1 2018, price = 60, discount = 5}");
        expected.append(separator);
        expected.append("{name = 'Beef', createDate = 1 1 2018, expireDate = 5 1 2018, price = 500, discount = 100}");
        expected.append(separator);
        expected.append("{name = 'Banana', createDate = 1 1 2018, expireDate = 5 1 2018, price = 100, discount = 5}");
        expected.append(separator);
        expected.append("{name = 'White Bread', createDate = 1 1 2018, expireDate = 3 1 2018, price = 50, discount = 3}");
        expected.append(separator);

        assertThat(cq.toString(), is(expected.toString()));

    }

    /**
     * Test control quality by checking correct resort of products in each storage.
     */
    @Test
    public void thenChangeDateThenGetCorrectResort() {

        GregorianCalendar date = new GregorianCalendar(2018, GregorianCalendar.JANUARY, 6);

        ControlQuality cq = new ControlQuality(new ArrayList<>());

        cq.addStorage(new Shop("Crossroad", 10, date));
        cq.addStorage(new Warehouse("FoodCity", 100, date));
        cq.addStorage(new Trash("Poligon", 1000, date));

        for (Food food: products) {
            cq.sort(food);
        }

        date = new GregorianCalendar(2018, GregorianCalendar.JANUARY, 10);

        cq.resort(date);

        StringBuilder expected = new StringBuilder();
        expected.append("Crossroad capacity: 10 have following:");
        expected.append(separator);
        expected.append("{name = 'Milk', createDate = 6 1 2018, expireDate = 13 1 2018, price = 50, discount = 10}");
        expected.append(separator);
        expected.append("{name = 'Apple', createDate = 1 1 2018, expireDate = 30 1 2018, price = 70, discount = 5}");
        expected.append(separator);
        expected.append("FoodCity capacity: 100 have following:");
        expected.append(separator);
        expected.append("Poligon capacity: 1000 have following:");
        expected.append(separator);
        expected.append("{name = 'Orange', createDate = 1 1 2018, expireDate = 7 1 2018, price = 90, discount = 30}");
        expected.append(separator);
        expected.append("{name = 'Bologna sausage', createDate = 21 12 2017, expireDate = 7 1 2018, price = 500, discount = 100}");
        expected.append(separator);
        expected.append("{name = 'Sour Cream', createDate = 1 1 2018, expireDate = 7 1 2018, price = 70, discount = 10}");
        expected.append(separator);
        expected.append("{name = 'Rye bread', createDate = 1 1 2018, expireDate = 3 1 2018, price = 60, discount = 5}");
        expected.append(separator);
        expected.append("{name = 'Beef', createDate = 1 1 2018, expireDate = 5 1 2018, price = 500, discount = 100}");
        expected.append(separator);
        expected.append("{name = 'Banana', createDate = 1 1 2018, expireDate = 5 1 2018, price = 100, discount = 5}");
        expected.append(separator);
        expected.append("{name = 'White Bread', createDate = 1 1 2018, expireDate = 3 1 2018, price = 50, discount = 3}");
        expected.append(separator);

        assertThat(cq.toString(), is(expected.toString()));


    }

}