package ru.job4j.hiberwrapper.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.hiberwrapper.model.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BasicDAOTest {

    private final BasicDAO<Item> dao = new BasicDAO<>();

    @Before
    public void setup() {
        this.dao.executeQuery("Truncate table item restart identity and commit no check");
    }

    @Test
    public void whenAddNewItemThenGetItemId() {
        Item item = new Item();
        item.setName("test");

        this.dao.saveOrUpdate(item);
        List<Item> items = this.dao.getAll(Item.class);

        assertThat(items.get(0), is(item));
    }

    @Test
    public void whenUpdateItemThenGetUpdatedResult() {
        Item item = new Item();
        item.setName("test");
        this.dao.saveOrUpdate(item);
        item.setName("updated");
        this.dao.saveOrUpdate(item);

        List<Item> items = this.dao.getAll(Item.class);

        assertThat(items.get(0), is(item));
    }

    @Test
    public void whenDeleteItemThenGetEmptyList() {
        Item item = new Item();
        item.setName("test");
        this.dao.saveOrUpdate(item);

        this.dao.delete(item);
        List<Item> items = this.dao.getAll(Item.class);

        assertTrue(items.isEmpty());
    }

    @Test
    public void whenSearchItemByIdThenGetCorrectItem() {
        Item expected = new Item();
        expected.setName("test");
        this.dao.saveOrUpdate(expected);

        Item result = this.dao.findById(Item.class, expected.getId());

        assertThat(result, is(expected));
    }

    @Test
    public void whenRequestItemsListThenGetAllItems() {
        Item first = new Item();
        first.setName("first");
        Item second = new Item();
        second.setName("second");
        this.dao.saveOrUpdate(first);
        this.dao.saveOrUpdate(second);
        List<Item> expected = new ArrayList<>(Arrays.asList(first, second));

        List<Item> result = this.dao.getAll(Item.class);

        assertThat(result, is(expected));
    }
}