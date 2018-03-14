package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты для класса Tracker.
 */
public class TrackerTest {
    /**
     * Тест на добавление заявки.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }

    /**
     * Тест на обновление заявки.
     */
    //todo check
    @Test
    public void whenUpdateNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDesc1", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDesc2", 1234L);
        next.setId(previous.getId());
        tracker.update(next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));

        System.out.println(tracker.findAll());
    }

    /**
     * Тест на удаление заявки.
     */
    @Test
    public void tryDelete() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "desc1", 1L);
        Item second = new Item("test2", "desc2", 2L);
        Item third = new Item("test3", "desc3", 3L);
        Item fourth = new Item("test4", "desc4", 4L);
        Item fifth = new Item("test5", "desc5", 5L);
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        tracker.add(fourth);
        tracker.add(fifth);
        tracker.delete(third);
        List<Item> expected = new ArrayList<>(Arrays.asList(first, second, fourth, fifth));
        assertThat(tracker.findAll(), is(expected));
    }

    /**
     * Тест на поиск все добавленных заявок.
     */
    @Test
    public void tryFindAll() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "desc1", 1L);
        Item second = new Item("test2", "desc2", 2L);
        Item third = new Item("test3", "desc3", 3L);
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        List<Item> expected = new ArrayList<>(Arrays.asList(first, second, third));
        assertThat(tracker.findAll(), is(expected));
    }

    /**
     * Тест на поиск заявок по имени.
     */
    @Test
    public void tryFindByName() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "desc1", 1L);
        Item second = new Item("test2", "desc2", 2L);
        Item third = new Item("test1", "desc3", 3L);
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        List<Item> expected = new ArrayList<>(Arrays.asList(first, third));
        assertThat(tracker.findByName("test1"), is(expected));
    }

    /**
     * Тест на поиск заявок по id.
     */
    @Test
    public void tryFindById() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "desc1", 1L);
        tracker.add(first);
        String id = first.getId();
        assertThat(tracker.findAll().get(0).getId(), is(id));
    }
}
