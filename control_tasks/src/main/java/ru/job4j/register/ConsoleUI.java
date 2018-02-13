package ru.job4j.register;

import ru.job4j.register.model.BasicAction;
import ru.job4j.register.model.Event;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Console UI.
 * @author aeremeev.
 * @version 1
 * @since 31.01.2018
 */
public class ConsoleUI {
    /**
     * Time constants.
     */
    private final static long MINUTE = 1000 * 60;
    private final static long HOUR = 1000 * 60 * 60;
    private final static long DAY = 1000 * 60 * 60 * 24;

    /**
     * UI actions map.
     */
    private final Map<String, BasicAction> actions;
    /**
     * Menu action item pointer.
     */
    private int menuItem = 0;

    /**
     * Default constructor.
     */
    public ConsoleUI() {
        this.actions = new LinkedHashMap<>();
    }

    /**
     * Actions map getter.
     * @return actions map.
     */
    public Map<String, BasicAction> getActions() {
        return this.actions;
    }

    /**
     * Fill actions map.
     */
    public void fillActions() {
        this.actions.put(String.valueOf(++menuItem), new ShowEvents("Events for last minute", MINUTE));
        this.actions.put(String.valueOf(++menuItem), new ShowEvents("Events for last hour", HOUR));
        this.actions.put(String.valueOf(++menuItem), new ShowEvents("Events for last day", DAY));
    }

    /**
     * Display menu items in console.
     */
    public void showActions() {
        for (String key: this.actions.keySet()) {
            System.out.println(String.format("%s. %s", key, this.actions.get(key).info()));
        }
    }

    /**
     * Show events.
     */
    private class ShowEvents extends BasicAction {
        /**
         * Time of query.
         */
        private long time;

        /**
         * Constructor.
         * @param description description.
         * @param time time of query.
         */
        public ShowEvents(String description, long time) {
            super(description);
            this.time = time;
        }

        @Override
        public void execute(Register register) {
            Set<Event> result = register.get(time);
            for (Event event: result) {
                System.out.println(event);
            }
            System.out.println(String.format("Count of events: %d", result.size()));
        }
    }
}
