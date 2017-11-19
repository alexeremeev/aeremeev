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


//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter writer = new PrintWriter(resp.getOutputStream());
//        writer.append("<h1>Update User</h1>");
//        String login = req.getParameter("login");
//        User user = UserStorage.getInstance().findByLogin(login);
//
//        writer.append("<form action='update' method='post'>");
//        writer.append("<table>");
//        writer.append(String.format("<tr><td></td><td><input type='hidden' name='login' value='%s'/></td></tr>", user.getLogin()));
//        writer.append(String.format("<tr><td>Name:</td><td><input type='text' name='name' value='%s'/></td></tr>", user.getName()));
//        writer.append(String.format("<tr><td>Email:</td><td><input type='email' name='email' value='%s'/></td></tr>", user.getEmail()));
//        writer.append("<tr><td><input type='submit' value='Save Changes'/></td></tr>");
//        writer.append("</table>");
//        writer.append("</form>");
//        writer.flush();
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String login = req.getParameter("login");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        User user = new User(name, login, email, System.currentTimeMillis());
        if (UserStorage.getInstance().updateUser(user)) {
            resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
        } else {
            writer.append("Error occurred while updating user!");
            writer.flush();
        }
    }
}
