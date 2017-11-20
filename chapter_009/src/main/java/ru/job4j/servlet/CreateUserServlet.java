package ru.job4j.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * class CreateUserServlet - Servlet создания нового пользователя.
 */
public class CreateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User(name, login, email, System.currentTimeMillis());
        if (UserStorage.getInstance().addUser(user)) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            writer.append("Login already exists! Try again!");
            writer.append("<br/><a href='create'>Create New User</a>");
            writer.append("<br/><a href='get'>View All Users</a>");
            writer.flush();
        }
    }
}
