package ru.job4j.orderbook;
import org.junit.Test;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса Exchange.
 */
public class ExchangeTest {
    /**
     * Тест парсинга файла с 10 ордерами с комбинированием совпавших ордеров и выводом.
     */
    @Test
    public void whenExchangeSmallXMLFileThenPrintSimpleTable() {
        ManualParser mp = new ManualParser("c:/projects/aeremeev/chapter_005/"
                + "src/test/java/ru/job4j/orderbook/ordersExchange.xml");
        Exchange ex = new Exchange();
        ex.load(mp.init());
        StringBuilder builder = new StringBuilder();
        builder.append(format("%n          Order book: book-1%n"));
        builder.append(format("%n         BID         -         ASK%n"));
        builder.append(format("     99,80@64        -    100,00@162       %n"));
        builder.append(format("     99,70@16        -    100,20@42        %n"));
        builder.append(format("     99,50@86        -    100,40@75        %n"));
        builder.append(format("     99,40@78        -    100,50@160       %n"));
        assertThat(ex.toString(), is(builder.toString()));
    }

    /**
     * Тест парсинга и обработки основного файла orders.xml.
     */
    @Test
    public void whenParseAndExchangeOriginalFileThenRunTimeLessThanSixSeconds() {
        Long expected = 6000L;
        Long actual = System.currentTimeMillis();
        ManualParser mp = new ManualParser("d:/orders.xml");
        Exchange ex = new Exchange();
        ex.load(mp.init());
        System.out.println(ex.toString());
        actual = System.currentTimeMillis() - actual;
        System.out.println(format("Runtime in ms is %s", actual));
        assertThat(actual < expected, is(true));
    }

}
