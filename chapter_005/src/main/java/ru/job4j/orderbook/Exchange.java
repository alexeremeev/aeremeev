package ru.job4j.orderbook;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * class Exchange - разбивает заявки по книгам.
 */
public class Exchange {
    /**
     * Карта книг заявок.
     */
    private Map<String, OrderBook> books;

    /**
     * Конструктор.
     */
    public Exchange() {
        this.books = new HashMap<>();
    }

    /**
     * Метод загружает и обрабатывает  Set заявок.
     * @param orders Set заявок.
     */
    public void load(TreeSet<Order> orders) {
        this.books = orders.stream().collect(Collectors.toMap(
                Order::getBook, order -> new OrderBook(order.getBook()), (order1, order2) -> order1));
        orders.forEach(order -> this.books.get(order.getBook()).addOrder(order));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.books.forEach((book, orderBook) -> builder.append(orderBook.toString()));
        return builder.toString();
    }
}
