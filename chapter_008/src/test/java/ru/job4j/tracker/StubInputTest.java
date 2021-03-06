package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.database.Database;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.StubInput;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Тесты ввода заглушками.
 */
public class StubInputTest {
    private final Settings settings = new Settings("tracker.properties");
    String url = this.settings.getSettings("DB_url");
    String username = this.settings.getSettings("DB_username");
    String password = this.settings.getSettings("DB_password");
    private final Database base = new Database();
    private final Tracker tracker = new Tracker(this.base, this.settings);

    /**
     * Предварительно сбрасываем таблицу.
     */
    @Before
    public void clearTable() {
        this.base.setConnection(this.url, this.username, this.password);
        this.base.execute(this.settings.getSettings("SQL_CLEAR_TABLE"));
        this.base.endConnection();
    }

    /**
     * Тест на добавление нового элемента.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {

        Input input = new StubInput(new String[]{"0", "test", "desc", "6"});
        new StartUi(input, this.tracker, this.base, this.settings).init();
        this.base.setConnection(this.url, this.username, this.password);
        assertThat(this.tracker.findAll().get(0).getName(), is("test"));
        this.base.endConnection();
    }

    /**
     * Тест на редактирование элемента.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        this.base.setConnection(this.url, this.username, this.password);
        this.tracker.add(new Item("replace", "replace desc", System.currentTimeMillis()));
        this.base.endConnection();
        Input input = new StubInput(new String[]{"2", "1", "test name", "desc", "6"});
        new StartUi(input, this.tracker, this.base, this.settings).init();
        this.base.setConnection(this.url, this.username, this.password);
        assertThat(this.tracker.findById(1).getName(), is("test name"));
        this.base.endConnection();
    }
    /**
     * Тест на поиск всех добавленных элементов.
     */
    @Test
    public void whenUserAddTwoItemsThenTrackerFindAllTwoItems() {
        Input input = new StubInput(new String[]{"0", "first", "first desc", "0", "second", "second desc", "1", "6"});
        new StartUi(input, this.tracker, this.base, this.settings).init();
        this.base.setConnection(this.url, this.username, this.password);
        assertThat(this.tracker.findAll().get(1).getName(), is("second"));
        this.base.endConnection();
    }

    /**
     * Тест на удаление элемента.
     */
    @Test
    public void whenUserAddItemAndDeleteThenTrackerFindAllIsEmpty() {
        this.base.setConnection(this.url, this.username, this.password);
        Item item = this.tracker.add(new Item("test", "test desc", System.currentTimeMillis()));
        this.base.endConnection();
        Input input = new StubInput(new String[]{"3", "1", "6"});
        new StartUi(input, this.tracker, this.base, this.settings).init();
        this.base.setConnection(this.url, this.username, this.password);
        assertThat(tracker.findAll().isEmpty(), is(true));
        this.base.endConnection();
    }

    /**
     * Тест на поиск по имени.
     */
    @Test
    public void whenUserAddTwoItemsWithIdenticalNamesThenTrackerFindByNameTwoItems() {
        this.base.setConnection(this.url, this.username, this.password);
        this.tracker.add(new Item("first", "first desc", System.currentTimeMillis()));
        this.tracker.add(new Item("first", "second desc", System.currentTimeMillis()));
        this.base.endConnection();
        Input input = new StubInput(new String[]{"5", "first", "6"});
        new StartUi(input, this.tracker, this.base, this.settings).init();
        this.base.setConnection(this.url, this.username, this.password);
        assertThat(tracker.findByName("first").get(1).getName(), is("first"));
        this.base.endConnection();
    }
    /**
     * Тест на поиск по ID.
     */
    @Test
    public void whenAddItemThenTrackerFindByIdSameItem() {
        this.base.setConnection(this.url, this.username, this.password);
        Item first = tracker.add(new Item("first", "first desc", System.currentTimeMillis()));
        first.setId(1);
        this.base.endConnection();
        Input input = new StubInput(new String[]{"4", "1", "6"});
        new StartUi(input, this.tracker, this.base, this.settings).init();
        this.base.setConnection(this.url, this.username, this.password);
        assertThat(tracker.findById(1), is(first));
        this.base.endConnection();
    }

}
