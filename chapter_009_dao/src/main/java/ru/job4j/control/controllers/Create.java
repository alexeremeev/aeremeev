package ru.job4j.control.controllers;

import com.google.gson.JsonObject;
import ru.job4j.control.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * class Create - сервлет создания нового пользователя.
 */
public class Create extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean result = false;
        UserRepository repository = new UserRepository();
        int create = repository.createReference(req.getParameter("name"), req.getParameter("password"),
                req.getParameter("address"), req.getParameter("role"), req.getParameter("musicType"));

        if (create != 0) {
            result = true;
        }

        JsonObject json = new JsonObject();
        PrintWriter writer = resp.getWriter();
        json.addProperty("success", result);
        writer.append(json.toString());
        writer.flush();
    }
}
