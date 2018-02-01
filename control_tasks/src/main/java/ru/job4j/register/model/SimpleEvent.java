package ru.job4j.register.model;

/**
 * Simple event.
 * @author aeremeev.
 * @version 1
 * @since 31.01.2018
 */
public final class SimpleEvent extends Event {
    /**
     * Constructor.
     * @param id ID.
     * @param time time of occurrence.
     */
    public SimpleEvent(int id, long time) {
        super(id, time);
    }
}
