package ru.job4j.servlet.controller;

import ru.job4j.servlet.UserStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class DeleteRoleServlet - servlet удаления роли пользователя.
 */
public class DeleteRoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int id = Integer.valueOf(req.getParameter("id"));
        UserStorage.getInstance().deleteRole(UserStorage.getInstance().findRole(id));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
