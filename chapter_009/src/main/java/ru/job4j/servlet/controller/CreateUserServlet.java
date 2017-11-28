package ru.job4j.servlet.controller;

import ru.job4j.servlet.UserStorage;
import ru.job4j.servlet.model.Address;
import ru.job4j.servlet.model.Role;
import ru.job4j.servlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class CreateUserServlet - Servlet создания нового пользователя.
 */
public class CreateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", UserStorage.getInstance().getUsers());
        req.setAttribute("roles", UserStorage.getInstance().getRoles());
        req.setAttribute("countries", UserStorage.getInstance().getCountries());
        req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String cityId = req.getParameter("city");
        Address address = new Address();
        address.setCityId(Integer.valueOf(cityId));
        Role role = new Role("misc");
        role.setId(Integer.valueOf(req.getParameter("role")));
        User user = new User(name, login, email, System.currentTimeMillis(), password);
        user.setRole(role);
        user.setAddress(address);
        if (UserStorage.getInstance().addUser(user)) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Login already exists! Try again!");
            doGet(req, resp);
        }
    }
}
