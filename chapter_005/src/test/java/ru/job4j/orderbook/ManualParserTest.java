package ru.job4j.orderbook;
import org.junit.Test;

import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса ManualParser.
 */
public class ManualParserTest {
    /**
     * Тест парсинга малого XML файла.
     */
    @Test
    public void tryParseXMLInShortTime() {
        ManualParser mp = new ManualParser("c:/projects/aeremeev/chapter_005/"
                 + "src/test/java/ru/job4j/orderbook/ordersPrsTst.xml");
        TreeSet<Order> parsedOrders = mp.init();
        TreeSet<Order> expected = new TreeSet<>();
        expected.add(new Order("book-1", "SELL", 100.5f, 81, 1));
        expected.add(new Order("book-3", "BUY", 99.5f, 86, 2));
        expected.add(new Order("book-3", "SELL", 100.0f, 80, 4));
        expected.add(new Order("book-2", "SELL", 100.5f, 79, 5));
        assertThat(parsedOrders, is(expected));
    }
}
