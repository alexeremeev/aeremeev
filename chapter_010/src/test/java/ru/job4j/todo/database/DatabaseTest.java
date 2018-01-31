package ru.job4j.todo.database;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.todo.models.Item;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * Database Test
 * @author aeremeev.
 * @version 1
 * @since 31.01.2018
 */
public class DatabaseTest {

    private final Database database = new Database();

    /**
     * Clear table.
     */
    @Before
    public void clearTable() {
        database.clearTable();
    }

    /**
     * Test of adding item.
     */
    @Test
    public void whenAddItemToToDoThenGetItem() {
        Item item = new Item();
        item.setDescription("test");
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        database.createOrUpdate(item);

        List<Item> result = database.getItems(true);

        assertThat(result.get(0), is(item));
    }

    /**
     * Test of finding item by id.
     */
    @Test
    public void whenTryFindItemByIdThenGetItem() {
        Item item = new Item();
        item.setDescription("test");
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        database.createOrUpdate(item);

        Item result = database.findById(item.getId());

        assertThat(result, is(item));
    }

    /**
     * Test of deleting item.
     */
    @Test
    public void whenDeleteItemFromToDoListThenGetEmptyList() {
        Item item = new Item();
        item.setDescription("test");
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        database.createOrUpdate(item);
        database.delete(item);

        List<Item> result = database.getItems(true);

        assertThat(result.isEmpty(), is(true));
    }

}