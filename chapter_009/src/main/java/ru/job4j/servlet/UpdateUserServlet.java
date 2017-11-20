package ru.job4j.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TODO
 */
public class UpdateUserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        req.setAttribute("user", UserStorage.getInstance().findByLogin(login));
        req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        User user = new User(name, login, email, System.currentTimeMillis());
        if (UserStorage.getInstance().updateUser(user)) {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            writer.append("Error occurred while updating user!");
            writer.flush();
        }
    }
}
