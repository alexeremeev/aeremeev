package ru.job4j.synchronize;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса Count.
 */
public class CountTest {
    /**
     * Тест работы потоков с синхоноизорованными методом.
     */
    @Test
    public void whenRunTwoThreadsThenNoRaceCondition() {
        Count counter = new Count();
        int expected = 600;
        Thread threadFirst = new Thread() {
            @Override
            public void run() {
                for (int index = 0; index != 200; index++) {
                    counter.increment(1);
                }
            }
        };
        Thread threadSecond = new Thread() {
            @Override
            public void run() {
                for (int index = 0; index != 200; index++) {
                    counter.increment(2);
                }
            }
        };
        try {
            threadFirst.start();
            threadSecond.start();
            threadFirst.join();
            threadSecond.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        assertThat(counter.getCount(), is(expected));
    }
}
