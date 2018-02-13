package ru.job4j.register.model;

import java.sql.Timestamp;

/**
 * Abstract event.
 * @author aeremeev.
 * @version 1
 * @since 31.01.2018
 */
public abstract class Event {
    /**
     * ID.
     */
    private final int id;
    /**
     * Time of occurrence.
     */
    private final long time;

    /**
     * Constructor.
     * @param id ID.
     * @param time time of occurrence.
     */
    public Event(int id, long time) {
        this.id = id;
        this.time = time;
    }

    /**
     * Time getter.
     * @return time.
     */
    public long getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("id=").append(id);
        sb.append(", time=").append(new Timestamp(time).toString());
        sb.append('}');
        return sb.toString();
    }
}
