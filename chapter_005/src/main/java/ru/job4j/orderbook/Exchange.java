package ru.job4j.orderbook;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

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
        for (Order order: orders) {
            String book = order.getBook();
            if (!this.books.containsKey(book)) {
                this.books.put(book, new OrderBook(book));
            }
            this.books.get(book).addOrder(order);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String book: new TreeSet<>(this.books.keySet())) {
            builder.append(books.get(book).toString());
        }
        return builder.toString();
    }
}
