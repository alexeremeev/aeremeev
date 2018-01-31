package ru.job4j.todo.servlets;

import ru.job4j.todo.database.Database;
import ru.job4j.todo.models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UpdateServlet - sets new done state for item.
 * @author aeremeev.
 * @version 1
 * @since 29.01.2018
 */
public class UpdateServlet extends HttpServlet {

    private final Database database = new Database();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        boolean isDone = Boolean.valueOf(req.getParameter("value"));
        Item item = database.findById(id);
        item.setDone(!isDone);
        database.createOrUpdate(item);
    }
}
