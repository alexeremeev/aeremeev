package ru.job4j.register;

import ru.job4j.register.model.Event;

import java.util.List;

/**
 * Register interface.
 * @author aeremeev.
 * @version 1
 * @since 31.01.2018
 */
public interface Register {
    /**
     * Add event to register.
     * @param event event.
     */
    void add(Event event);

    /**
     * Get list of events by time query.
     * @param time time of query.
     * @return list of events.
     */
    List<Event> get(long time);
}
