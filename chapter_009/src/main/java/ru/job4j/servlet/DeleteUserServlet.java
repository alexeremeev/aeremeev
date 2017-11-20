package ru.job4j.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class DeleteUserServlet - Servlet для удаления пользователя.
 */
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        UserStorage.getInstance().deleteUser(UserStorage.getInstance().findByLogin(login));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
