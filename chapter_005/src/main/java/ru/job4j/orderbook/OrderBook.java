package ru.job4j.orderbook;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import static java.lang.String.format;

/**
 * class OrderBook - книга заявок покупки / продажи.
 */
public class OrderBook {
    /**
     * Название книги.
     */
    private String name;
    /**
     * Сет заявок  покупки / продажи.
     */
    private TreeSet<MatchingOrders> ordersSet;
    /**
     * Константа для типа операции покупка.
     */
    private static final String BUY = "BUY";
    /**
     * Константа для запроса.
     */
    private static final String ASK = "ASK";
    /**
     * Константа для ставки.
     */
    private static final String BID = "BID";

    /**
     * Конструктор.
     * @param name имя книги.
     */
    public OrderBook(String name) {
        this.name = name;
        this.ordersSet = new TreeSet<>();
    }

    /**
     * Метод добавляет новую заявку в книгу.
     * @param order заявка.
     */
    public void addOrder(Order order) {
        MatchingOrders mOrder = new MatchingOrders(order);
        if (this.ordersSet.contains(mOrder)) {
            mOrder = this.ordersSet.ceiling(mOrder);
            mOrder.volume = mOrder.volume + (BUY.equals(order.getOperation()) ? -order.getVolume() : order.getVolume());
        } else {
            this.ordersSet.add(mOrder);
        }
        if (mOrder.volume == 0) {
            this.ordersSet.remove(mOrder);
        }
    }

    /**
     * Метод создает список всех заявок на покупку.
     * @return список заявок на покупку.
     */
    private List<MatchingOrders> bidList() {
        List<MatchingOrders> result = new LinkedList<>();
        Iterator<MatchingOrders> it = this.ordersSet.descendingIterator();
        while (it.hasNext()) {
            MatchingOrders order = it.next();
            if (order.volume < 0) {
                result.add(order);
            }
        }
        return result;
    }
    /**
     * Метод создает список всех заявок на продажу.
     * @return список заявок на продажу.
     */
    private List<MatchingOrders> askList() {
        List<MatchingOrders> result = new LinkedList<>();
        Iterator<MatchingOrders> it = this.ordersSet.iterator();
        while (it.hasNext()) {
            MatchingOrders order = it.next();
            if (order.volume > 0) {
                result.add(order);
            }
        }
        return result;
    }
    /**
     * Переопределенный toString().
     * @return возващает книгу заявок в виде:
     *       Order book: name
     *   BID            -         ASK
     * price@volume     -    price@volume
     */
    @Override
    public String toString() {
        Iterator<MatchingOrders> ask = this.askList().iterator();
        Iterator<MatchingOrders> bid = this.bidList().iterator();

        StringBuilder builder = new StringBuilder();
        builder.append(format("%n%22s%s%n", "Order book: ", this.name));
        builder.append(format("%n%12s%10s%12s%n", BID, "-", ASK));

        while ((ask.hasNext() && bid.hasNext())) {
            builder.append(format("%21s-%12s%n", bid.next(), ask.next()));
        }
        while (bid.hasNext()) {
            builder.append(format("%21s-%12s%n", bid.next(), "---"));
        }
        while (ask.hasNext()) {
            builder.append(format("%12s%10s%21s%n", "---", "-", ask.next()));
        }
        return builder.toString();
    }

    /**
     * Внутренний класс MatchingOrders - класс.
     * Определяет совпадение заявок покупки / продажи по цене.
     */
    private class MatchingOrders implements Comparable<MatchingOrders> {
        /**
         * Цена.
         */
        private float price;
        /**
         * Количество.
         */
        private int volume;

        /**
         * Конструктор.
         * @param order заявка покупки / продажи.
         */
        private MatchingOrders(Order order) {
            this.price = order.getPrice();
            this.volume = BUY.equals(order.getOperation()) ? -order.getVolume() : order.getVolume();
        }

        /**
         * Сравниваем заявки только по цене.
         * @param order заявка.
         * @return результат сравнения.
         */
        @Override
        public int compareTo(MatchingOrders order) {
            return Float.compare(price, order.price);
        }

        @Override
        public boolean equals(Object object) {
            boolean result = false;
            if (this == object) {
                result = true;
            } else {
                if (object != null && getClass() == object.getClass()) {
                    MatchingOrders order = (MatchingOrders) object;
                    result = Float.compare(order.price, price) == 0;
                }
            }
            return result;
        }

        /**
         * Переопределенный hashCode() по цене заявки.
         * @return битовое значение цены.
         */
        @Override
        public int hashCode() {
            return (price != +0.0f ? Float.floatToIntBits(price) : 0);
        }

        /**
         * Переопрделенный метод toString().
         * @return строка вида price@volume.
         */
        @Override
        public String toString() {
            return format("%10.2f@%-10d", price, Math.abs(volume));
        }

    }
}
