package ru.job4j.control.controllers;

import ru.job4j.control.dao.PSQLpool;
import ru.job4j.control.models.User;
import ru.job4j.control.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * LoginController - сервлет входа в систему.
 */
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        PSQLpool.getInstance().getAddressDAO().clearTable();
        PSQLpool.getInstance().getMusicTypeDAO().clearTable();
        PSQLpool.getInstance().getMusicTypeDAO().clearTableUserMusicTypes();
        PSQLpool.getInstance().getRoleDAO().clearTable();
        PSQLpool.getInstance().getUserDAO().clearTable();
        PSQLpool.getInstance().getRoleDAO().fillRoles();
        PSQLpool.getInstance().getMusicTypeDAO().fillMusicType();
        UserRepository userRepository = new UserRepository();
        userRepository.createReference("admin", "admin", "Moscow", "admin", "Pop", "Rock");
        userRepository.createReference("mandator", "mandator", "Petersburg", "mandator",  "Rock", "Rap");
        userRepository.createReference("user", "user", "Vladivostok", "user",  "Pop", "Rap");
        User user = PSQLpool.getInstance().getUserDAO().isCredential(name, password);
        if (user != null) {
            HttpSession session = req.getSession();
            synchronized (session) {
                session.setAttribute("name", name);
                session.setAttribute("role", user.getRoleId());
                session.setAttribute("user", user);
            }
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Invalid login / password!");
            doGet(req, resp);
        }
    }
}
