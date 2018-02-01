package ru.job4j.register;

import ru.job4j.register.model.Event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Simple register.
 * @author aeremeev.
 * @version 1
 * @since 31.01.2018
 */
public class SimpleRegister implements Register {
    /**
     * List of events.
     */
    private List<Event> events = new CopyOnWriteArrayList<>();

    /**
     * Add event to register.
     * @param event event.
     */
    @Override
    public void add(Event event) {
        this.events.add(event);
    }

    /**
     * Get list of events by time query.
     * @param time time of query. If time == 0 method returns list of all registered events.
     * @return list of events.
     */
    @Override
    public List<Event> get(long time) {
        List<Event> result = new CopyOnWriteArrayList<>();
        if (time == 0) {
            result = this.events;
        } else {
            long searchTime = System.currentTimeMillis() - time;
            for (Event event: this.events) {
                if (event.getTime() > searchTime) {
                    result.add(event);
                }
            }
        }
        return result;
    }
}
