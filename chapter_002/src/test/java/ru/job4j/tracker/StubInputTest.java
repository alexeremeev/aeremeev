package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тесты ввода заглушками.
 */
public class StubInputTest {
    /**
     * Тест на добавление нового элемента.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test", "desc", "6"});
        new StartUi(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test"));
    }

    /**
     * Тест на редактирование элемента.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("replace", "replace desc", System.currentTimeMillis()));
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
        new StartUi(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }
    /**
     * Тест на поиск всех добавленных элементов.
     */
    @Test
    public void whenUserAddTwoItemsThenTrackerFindAllTwoItems() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "first", "first desc", "0", "second", "second desc", "1", "6"});
        new StartUi(input, tracker).init();
        assertThat(tracker.findAll()[1].getName(), is("second"));
    }

    /**
     * Тест на удаление элемента.
     */
    @Test
    public void whenUserAddThreeItemsAndDeleteSecondTrackerFindAllTwoItems() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("first", "first desc", System.currentTimeMillis()));
        Item second = tracker.add(new Item("second", "second desc", System.currentTimeMillis()));
        Item third = tracker.add(new Item("third", "third desc", System.currentTimeMillis()));
        Input input = new StubInput(new String[]{"3", second.getId(), "6"});
        Item[] expected = {first, third};
        new StartUi(input, tracker).init();
        assertThat(tracker.findAll(), is(expected));
    }
    /**
     * Тест на поиск по имени.
     */
    @Test
    public void whenUserAddTwoItemsWithIdenticalNamesThenTrackerFindByNameTwoItems() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("first", "first desc", System.currentTimeMillis()));
        Item second = tracker.add(new Item("first", "second desc", System.currentTimeMillis()));
        Input input = new StubInput(new String[]{"5", "first", "6"});
        new StartUi(input, tracker).init();
        assertThat(tracker.findByName("first")[1].getName(), is("first"));
    }
    /**
     * Тест на поиск по ID.
     */
    @Test
    public void whenAddItemThenTrackerFindByIdSameItem() {
        Tracker tracker = new Tracker();
        Item first = tracker.add(new Item("first", "first desc", System.currentTimeMillis()));
        Input input = new StubInput(new String[]{"4", first.getId(), "6"});
        new StartUi(input, tracker).init();
        assertThat(tracker.findById(first.getId()), is(first));
    }

}
