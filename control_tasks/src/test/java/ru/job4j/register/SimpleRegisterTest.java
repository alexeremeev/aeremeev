package ru.job4j.register;

import org.junit.Test;
import ru.job4j.register.model.Event;
import ru.job4j.register.model.SimpleEvent;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleRegisterTest {

    private final static long MINUTE = 1000 * 60;
    private final static long HOUR = 1000 * 60 * 60;
    private final static long DAY = 1000 * 60 * 60 * 24;

    @Test
    public void whenAddEventToRegisterThenGetEventByTime() {
        int id = 0;
        Event dayEvent = new SimpleEvent(id++, System.currentTimeMillis() - DAY + MINUTE);
        Event hourEvent = new SimpleEvent(id++, System.currentTimeMillis() - HOUR + MINUTE);
        Event minuteEvent = new SimpleEvent(id++, System.currentTimeMillis());
        Register register = new SimpleRegister();
        register.add(dayEvent);
        register.add(hourEvent);
        register.add(minuteEvent);

        List<Event> days = register.get(DAY);
        assertTrue(days.size() == 3);
        assertThat(days.get(0), is(dayEvent));

        List<Event> hours = register.get(HOUR);
        assertTrue(hours.size() == 2);
        assertThat(hours.get(0), is(hourEvent));

        List<Event> minutes = register.get(MINUTE);
        assertTrue(minutes.size() == 1);
        assertThat(minutes.get(0), is(minuteEvent));
    }
}