package ru.job4j.servlet.controller;

import ru.job4j.servlet.UserStorage;
import ru.job4j.servlet.model.Address;
import ru.job4j.servlet.model.Role;
import ru.job4j.servlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * class UpdateUserServlet - servlet редактирования пользователя.
 */
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        req.setAttribute("user", UserStorage.getInstance().findByLogin(login));
        req.setAttribute("roles", UserStorage.getInstance().getRoles());
        req.setAttribute("countries", UserStorage.getInstance().getCountries());
        req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login;
        int id;
        HttpSession session = req.getSession(false);
        if (!(boolean) req.getSession().getAttribute("admin")) {
            login = (String) session.getAttribute("login");
            id = ((User) session.getAttribute("user")).getRole().getId();
        } else {
            login = req.getParameter("login");
            id = Integer.valueOf(req.getParameter("role"));
        }
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String cityId = req.getParameter("city");
        Address address = new Address();
        address.setCityId(Integer.valueOf(cityId));
        User user = new User(name, login, email, System.currentTimeMillis(), password);
        Role role = new Role("misc");
        role.setId(id);
        user.setRole(role);
        user.setAddress(address);
        if (UserStorage.getInstance().updateUser(user)) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Error occurred while updating user!");
            doGet(req, resp);
        }
    }
}
