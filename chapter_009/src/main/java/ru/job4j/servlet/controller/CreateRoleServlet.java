package ru.job4j.servlet.controller;

import ru.job4j.servlet.UserStorage;
import ru.job4j.servlet.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateRoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/createRole.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String admin = (req.getParameter("admin"));
        Role role = new Role(name);
        if (admin != null) {
            role.setAdmin(admin.equals("1"));
        }

        if (UserStorage.getInstance().addRole(role)) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Role already exists! Try again!");
            doGet(req, resp);
        }
    }
}
