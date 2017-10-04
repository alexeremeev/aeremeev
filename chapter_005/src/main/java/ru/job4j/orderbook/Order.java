package ru.job4j.orderbook;

/**
 * class Order - биржевая заявка на покупку / продажу.
 */
public class Order implements Comparable<Order> {
    /**
     * Книга заявок.
     */
    private String book;
    /**
     * Операция заявки: купля / продажа.
     */
    private String operation;
    /**
     * Цена операции.
     */
    private float price;
    /**
     * Количество акций.
     */
    private int volume;
    /**
     * Id заявки.
     */
    private int id;

    /**
     * Конструктор.
     * @param book книга заявок.
     * @param operation операция заявки: купля / продажа.
     * @param price цена операции.
     * @param volume количество акций.
     * @param id Id заявки.
     */
    public Order(String book, String operation, float price, int volume, int id) {
        this.book = book;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
        this.id = id;
    }

    /**
     * Геттер книги заявок.
     * @return книга заявок.
     */
    public String getBook() {
        return book;
    }

    /**
     * Геттер операции.
     * @return операция заявки: купля / продажа.
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Геттер цены.
     * @return цена операции.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Геттер количества.
     * @return количество акций.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Геттер ID.
     * @return Id заявки.
     */
    public int getId() {
        return id;
    }

    /**
     * Переопределенный hashCode().
     * @return Id заявки.
     */
    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public int compareTo(Order order) {
        return Integer.compare(id, order.id);
    }

}
