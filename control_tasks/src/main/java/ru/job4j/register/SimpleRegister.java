package ru.job4j.register;

import ru.job4j.register.model.Event;
import ru.job4j.register.model.SimpleEvent;

import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Simple register.
 * @author aeremeev.
 * @version 1.1
 * @since 31.01.2018
 */
public class SimpleRegister implements Register {
    /**
     * Set of events.
     */
    private NavigableSet<Event> events = new ConcurrentSkipListSet<>();

    /**
     * Add event to register.
     * @param event event.
     */
    @Override
    public void add(Event event) {
        this.events.add(event);
    }

    /**
     * Get set of events by time query.
     * @param time time of query. If time == 0 method returns list of all registered events.
     * @return set of events.
     */
    @Override
    public Set<Event> get(long time) {
        Event base = new SimpleEvent(-1, System.currentTimeMillis() - time);
        Set<Event> result;
        if (time == 0) {
            result = this.events;
        } else {
            result = events.tailSet(events.ceiling(base));

        }
        return result;
    }
}
