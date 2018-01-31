package ru.job4j.todo.servlets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ru.job4j.todo.database.Database;
import ru.job4j.todo.models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

/**
 * MainView - view all items and add new.
 * @author aeremeev.
 * @version 1
 * @since 29.01.2018
 */
public class MainView extends HttpServlet {

    private final Database database = new Database();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        boolean all = Boolean.valueOf(req.getParameter("done"));
        List<Item> items = database.getItems(all);
        JsonObject object = new JsonObject();
        object.addProperty("items", this.listToJsonArray(items).toString());
        PrintWriter writer = resp.getWriter();
        writer.append(object.toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        Item item = new Item();
        item.setDescription(req.getParameter("description"));
        item.setDone(Boolean.valueOf(req.getParameter("done")));
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        int id = database.createOrUpdate(item);
        JsonObject result = new JsonObject();

        boolean success = false;

        if (id > 0) {
            success = true;
        }

        result.addProperty("Success", success);

        PrintWriter writer = resp.getWriter();

        writer.append(result.toString());
        writer.flush();
    }

    /**
     * Converts list of items to JSON array.
     * @param items list of items
     * @return JSON array.
     */
    public JsonArray listToJsonArray(List<Item> items) {
        JsonArray result = new JsonArray();
        for (Item item: items) {
            JsonObject object = new JsonObject();
            object.addProperty("id", item.getId());
            object.addProperty("description", item.getDescription());
            object.addProperty("created", String.format("%1$TD %1$TT", item.getCreated()));
            object.addProperty("done", item.getDone());
            result.add(object);
        }
        return result;
    }
}
