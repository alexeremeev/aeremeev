package ru.job4j.servlet.controller;

import ru.job4j.servlet.UserStorage;
import ru.job4j.servlet.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * class UpdateRoleServlet - сервлет редактирования роли.
 */
public class UpdateRoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        req.setAttribute("role", UserStorage.getInstance().findRole(id));
        req.getRequestDispatcher("/WEB-INF/views/updateRole.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String name = req.getParameter("name");
        String admin = (req.getParameter("admin"));
        Role role = new Role(name);
        if (admin != null) {
            role.setAdmin(admin.equals("1"));
        }
        role.setId(Integer.valueOf(req.getParameter("id")));
        if (UserStorage.getInstance().updateRole(role)) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            writer.append("Error occurred while updating user!");
            writer.flush();
        }
    }
}
