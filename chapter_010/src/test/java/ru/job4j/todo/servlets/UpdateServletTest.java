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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Update servlet test.
 * @author aeremeev.
 * @version 1
 * @since 31.01.2018
 */

public class UpdateServletTest {

    private final Database database = new Database();

    /**
     * Clear table.
     */
    @Before
    public void clearTable() {
        database.clearTable();
    }

    /**
     * Test of changing item done flag.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Test
    public void whenItemIsDoneThenGetUpdatedItem() throws ServletException, IOException {
        Item item = new Item();
        item.setDescription("test");
        item.setDone(false);
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        database.createOrUpdate(item);

        UpdateServlet servlet = new UpdateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("value")).thenReturn("false");
        servlet.doPost(request, response);

        item.setDone(true);
        Item result = database.findById(1);

        assertThat(result, is(item));
    }
}