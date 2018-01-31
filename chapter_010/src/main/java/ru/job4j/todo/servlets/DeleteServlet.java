package ru.job4j.todo.servlets;

import ru.job4j.todo.database.Database;
import ru.job4j.todo.models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DeleteServlet - deletes item from db by id.
 * @author aeremeev.
 * @version 1
 * @since 29.01.2018
 */
public class DeleteServlet extends HttpServlet {

    private final Database database = new Database();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        Item item = new Item();
        item.setId(Integer.valueOf(idStr));
        database.delete(item);
    }
}
