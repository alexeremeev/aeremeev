package ru.job4j.servlet.controller;

import com.google.gson.Gson;
import ru.job4j.servlet.UserStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class JsonServlet - получение данных в формате Json для димнамического обновления списка городов.
 */
public class JsonServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String country = req.getParameter("country_id");
        String json = new Gson().toJson(UserStorage.getInstance().getCities(Integer.valueOf(country)));
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
