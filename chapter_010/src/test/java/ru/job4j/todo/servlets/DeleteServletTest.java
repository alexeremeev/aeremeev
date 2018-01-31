package ru.job4j.todo.servlets;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.todo.database.Database;
import ru.job4j.todo.models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * DeleteServlet Test
 * @author aeremeev.
 * @version 1
 * @since 31.01.2018
 */
public class DeleteServletTest {

    private final Database database = new Database();

    /**
     * Clear table.
     */
    @Before
    public void clearTable() {
        database.clearTable();
    }

    /**
     * Test of deleting item.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Test
    public void whenDeleteItemFromListThenGetEmptyList() throws ServletException, IOException {
        Item item = new Item();
        item.setDescription("test");
        item.setDone(true);
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        database.createOrUpdate(item);

        DeleteServlet servlet = new DeleteServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("id")).thenReturn("1");
        servlet.doPost(request, response);

        List<Item> result = database.getItems(true);

        assertTrue(result.isEmpty());
    }
}