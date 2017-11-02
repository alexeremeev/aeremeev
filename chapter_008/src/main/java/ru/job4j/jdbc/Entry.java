package ru.job4j.jdbc;

import javax.xml.bind.annotation.XmlElement;

/**
 * class Entry - представление одной строки в SQL таблице test.
 */
public class Entry {
    /**
     * Колонка field.
     */
    private String field;

    /**
     * Конструктор.
     * @param field колонка field.
     */
    public Entry(String field) {
        this.field = field;
    }

    /**
     * Геттер.
     * @return колонка field.
     */
    @XmlElement
    public String getField() {
        return field;
    }

    @Override
    public String toString() {
        return this.field;
    }
}
