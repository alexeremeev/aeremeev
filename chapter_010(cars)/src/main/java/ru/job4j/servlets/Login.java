package ru.job4j.servlets;

import com.google.gson.JsonObject;
import ru.job4j.dao.UserDAO;
import ru.job4j.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Login servlet. Validate existing user, or create new one.
 * @author aeremeev.
 * @version 1
 * @since 05.02.2018
 */
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        boolean success = (boolean) session.getAttribute("success");
        PrintWriter out = new PrintWriter(resp.getOutputStream());
        JsonObject json = new JsonObject();
        json.addProperty("success", success);
        out.append(json.toString());
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean register = Boolean.valueOf(req.getParameter("register"));
        HttpSession session = req.getSession();
        session.setAttribute("currentOrder", 0);
        session.setAttribute("success", false);

        JsonObject object = new JsonObject();
        object.addProperty("success", false);

        PrintWriter writer = new PrintWriter(resp.getWriter());

        UserDAO dao = new UserDAO();
        User user;

        if (register) {
            user = new User();
            user.setLogin(login);
            user.setPassword(password);
            dao.saveOrUpdate(user);
            if (user.getId() > 0) {
                session.setAttribute("success", true);
                object.addProperty("success", true);
            }
        } else {
            user = dao.isCredential(login, password);
            if (user != null) {
                session.setAttribute("success", true);
                object.addProperty("success", true);
            }
            session.setAttribute("user_id", user.getId());
            writer.append(object.toString());
            writer.flush();
        }
    }
}