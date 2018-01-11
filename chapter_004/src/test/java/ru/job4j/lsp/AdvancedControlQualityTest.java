package ru.job4j.lsp;

import org.junit.Test;
import ru.job4j.lsp.products.*;
import ru.job4j.lsp.storage.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Advanced Control Quality test.
 * @author aeremeev.
 * @version 1
 * @since 11.01.2018
 */
public class AdvancedControlQualityTest {

    private final String separator = System.getProperty("line.separator");

    /**
     * Test control quality by checking correct sort of products list.
     */
    @Test
    public void whenSortExpiredFoodsThenGetThenToRecycle() {
        GregorianCalendar date = new GregorianCalendar(2018, GregorianCalendar.JANUARY, 11);

        List<RecyclableFood> recyclableFoods = new ArrayList<>();
        List<StorageExtended> storageExtendeds = new ArrayList<>();

        Food apple = new Fruit("Apple", new GregorianCalendar(2018, GregorianCalendar.JANUARY, 1),
                new GregorianCalendar(2018, GregorianCalendar.JANUARY, 10), 70, 5);

        Food sourCream = new Dairy("Sour Cream", new GregorianCalendar(2018, GregorianCalendar.JANUARY, 1),
                new GregorianCalendar(2018, GregorianCalendar.JANUARY, 7), 80, 10);

        Food milk = new Dairy("Milk", new GregorianCalendar(2018, GregorianCalendar.JANUARY, 11),
                new GregorianCalendar(2018, GregorianCalendar.JANUARY, 18), 80, 10);

        Food banana = new Fruit("Banana", new GregorianCalendar(2018, GregorianCalendar.JANUARY, 11),
                new GregorianCalendar(2018, GregorianCalendar.JANUARY, 18), 70, 5);

        recyclableFoods.add(new RecyclableFruit(apple, true));
        recyclableFoods.add(new RecyclableDiary(milk, false));
        recyclableFoods.add(new RecyclableDiary(sourCream, true));
        recyclableFoods.add(new RecyclableFruit(banana, false));

        Storage warehouse = new Warehouse("FoodCity", 100, date);

        storageExtendeds.add(new Freezer("Big Freezer", 1000, date, -4));
        storageExtendeds.add(new WarehouseExtend("FoodCounty", 100, date, 4, warehouse));

        AdvancedControlQuality advancedCQ = new AdvancedControlQuality(new ArrayList<>(), storageExtendeds);
        advancedCQ.addStorage(warehouse);

        for (RecyclableFood recyclableFood: recyclableFoods) {
            advancedCQ.addFood(recyclableFood);
        }

        StringBuilder expected = new StringBuilder();
        expected.append("FoodCity capacity: 100 have following:");
        expected.append(separator);
        expected.append("{name = 'Milk', createDate = 11 1 2018, expireDate = 18 1 2018, price = 80, discount = 10}");
        expected.append(separator);
        expected.append("{name = 'Banana', createDate = 11 1 2018, expireDate = 18 1 2018, price = 70, discount = 5}");
        expected.append(separator);
        expected.append("Big Freezer capacity: 1000 have following:");
        expected.append(separator);
        expected.append("{name = 'Apple', createDate = 1 1 2018, expireDate = 10 1 2018, price = 70, discount = 5}");
        expected.append(separator);
        expected.append("{name = 'Sour Cream', createDate = 1 1 2018, expireDate = 7 1 2018, price = 80, discount = 10}");
        expected.append(separator);
        expected.append("FoodCounty capacity: 100 have following:");
        expected.append(separator);
        expected.append("{name = 'Milk', createDate = 11 1 2018, expireDate = 18 1 2018, price = 80, discount = 10}");
        expected.append(separator);
        expected.append("{name = 'Banana', createDate = 11 1 2018, expireDate = 18 1 2018, price = 70, discount = 5}");
        expected.append(separator);

        assertThat(advancedCQ.toString(), is(expected.toString()));

    }

}