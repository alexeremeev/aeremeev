package ru.job4j.userstorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * class UsersServlet - Servlet для User.
 */
public class UsersServlet extends HttpServlet {
    /**
     * Хранилище пользователей.
     */
    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String login = req.getParameter("login");
        if (login == null) {
            writer.append("Users: " + users.getUsers());
            writer.flush();
        } else if (users.findByLogin(login) == null) {
            writer.append("No user with this login found, try again!");
            writer.flush();
        } else {
            writer.append("User " + users.findByLogin(login));
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User(name, login, email, System.currentTimeMillis());
        users.addUser(user);
        doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        User user = new User(name, login, email, System.currentTimeMillis());
        users.updateUser(user);
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if (users.findByLogin(login) != null) {
            users.deleteUser(users.findByLogin(login));
            writer.append("User removed.");
            writer.flush();
        } else {
            writer.append("User not found.");
            writer.flush();
        }
    }
}
