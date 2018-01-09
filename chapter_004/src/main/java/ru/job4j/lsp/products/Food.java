package ru.job4j.lsp.products;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Food - abstract class of product.
 * @author aeremeev
 * @since 08.01.2018
 * @version 1
 */
public abstract class Food {
    /**
     * Product name.
     */
    private String name;
    /**
     * Product manufacture date.
     */
    private GregorianCalendar createDate;
    /**
     * Product expire date.
     */
    private GregorianCalendar expireDate;
    /**
     * Product price.
     */
    private int price;
    /**
     * Product applicable discount.
     */
    private int discount;

    /**
     * Constructor.
     * @param name product name.
     * @param createDate product manufacture date.
     * @param expireDate product expire date.
     * @param price product price.
     * @param discount product applicable discount.
     */
    public Food(String name, GregorianCalendar createDate, GregorianCalendar expireDate, int price, int discount) {
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.price = price;
        this.discount = discount;
    }

    /**
     * Apply discount to product price.
     */
    public void applyDiscount() {
        this.price = this.price - this.discount;
    }

    /**
     * Get percentage of product expiration date. 0 - fresh, >100 - garbage.
     * @param day current date.
     * @return percentage of product expiration date.
     */
    public int getPercentOfBestBefore(GregorianCalendar day) {
        int percent;
        Calendar today = day;
        double fullPeriod = (this.expireDate.getTimeInMillis() - this.createDate.getTimeInMillis()) / 1000;
        double currentPeriod = (today.getTimeInMillis() - this.createDate.getTimeInMillis()) / 1000;
        percent = (int) Math.abs((currentPeriod / fullPeriod) * 100);
        return percent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("name = '").append(name).append('\'');
        sb.append(", createDate = ").append(String.format("%d %d %d", createDate.get(createDate.DATE), createDate.get(createDate.MONTH) + 1, createDate.get(createDate.YEAR)));
        sb.append(", expireDate = ").append(String.format("%d %d %d", expireDate.get(expireDate.DATE), expireDate.get(createDate.MONTH) + 1, expireDate.get(createDate.YEAR)));
        sb.append(", price = ").append(price);
        sb.append(", discount = ").append(discount);
        sb.append('}');
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }
}
