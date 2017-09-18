package ru.job4j.control;

/**
 * class Account - банковский счет клиента.
 */
public class Account {
    /**
     * Сумма на счету.
     */
    private double value;
    /**
     * Реквизиты.
     */
    private int requisites;

    /**
     * Конструктор.
     * @param value внесенная сумма.
     * @param requisites реквизиты.
     */
    public Account(double value, int requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * Сеттер суммы на счету.
     * @param value сумма.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Геттер суммы на счету.
     * @return суммма на счету.
     */
    public double getValue() {
        return value;
    }
}
