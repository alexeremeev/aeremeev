package ru.job4j.register;

import org.junit.Test;
import ru.job4j.register.model.Event;
import ru.job4j.register.model.SimpleEvent;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

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

        Set<Event> expectedDays = new TreeSet<>(Arrays.asList(dayEvent, hourEvent, minuteEvent));
        Set<Event> expectedHours = new TreeSet<>(Arrays.asList(hourEvent, minuteEvent));
        Set<Event> expectedMinutes = new TreeSet<>(Arrays.asList(minuteEvent));


        Set<Event> days = register.get(DAY);
        assertTrue(days.size() == 3);
        assertThat(days, is(expectedDays));

        Set<Event> hours = register.get(HOUR);
        assertTrue(hours.size() == 2);
        assertThat(hours, is(expectedHours));

        Set<Event> minutes = register.get(MINUTE);
        assertTrue(minutes.size() == 1);
        assertThat(minutes, is(expectedMinutes));
    }
}