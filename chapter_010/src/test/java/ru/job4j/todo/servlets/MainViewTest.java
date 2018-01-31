package ru.job4j.todo.servlets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.todo.database.Database;
import ru.job4j.todo.models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * Main view test.
 * @author aeremeev.
 * @version 1
 * @since 31.01.2018
 */
public class MainViewTest {

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
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Test
    public void whenAddNewItemThenGetItem() throws ServletException, IOException {
        Item original = new Item();
        original.setId(1);
        original.setDescription("test");
        original.setDone(false);

        MainView servlet = new MainView();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(request.getParameter("description")).thenReturn("test");
        when(request.getParameter("done")).thenReturn("false");
        when(response.getWriter()).thenReturn(writer);
        servlet.doPost(request, response);

        writer.flush();
        Item result = database.findById(1);
        original.setCreated(result.getCreated());

        assertTrue(stringWriter.toString().contains("Success"));
        assertThat(result, is(original));
    }

    /**
     * Test of getting JSON string of item list.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Test
    public void whenGetItemThenReturnJSONString() throws ServletException, IOException {
        Item item = new Item();
        item.setDescription("test");
        item.setDone(true);
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        database.createOrUpdate(item);

        MainView servlet = new MainView();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(request.getParameter("done")).thenReturn("true");
        when(response.getWriter()).thenReturn(writer);
        servlet.doGet(request, response);
        writer.flush();

        JsonObject object = new JsonObject();
        object.addProperty("id", item.getId());
        object.addProperty("description", item.getDescription());
        object.addProperty("created", String.format("%1$TD %1$TT", item.getCreated()));
        object.addProperty("done", item.getDone());

        JsonArray array = new JsonArray();
        array.add(object);

        JsonObject expected = new JsonObject();
        expected.addProperty("items", array.toString());

        assertTrue(stringWriter.toString().equals(expected.toString()));
    }
}