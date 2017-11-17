package ru.job4j.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

/**
 * class GetUserServlet - Servlet вывода всех пользователей.
 */
public class GetUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<a href='create'>Add New User</a>");
        writer.append("<h1>Users List</h1>");

        List<User> allUsers = UserStorage.getInstance().getUsers();

        writer.append("<table border='1' width='100%'");
        writer.append("<tr><th>Name</th><th>Login</th><th>Email</th><th>CreateDate</th><th>Edit</th><th>Delete</th></tr>");

        for (User user : allUsers) {
            writer.append(String.format("<tr><td> %s </td><td> %s </td><td> %s </td><td> %s </td>",
                    user.getName(), user.getLogin(), user.getEmail(), new Timestamp(user.getCreateDate())));
            writer.append(String.format("<td><form><input type='button' value='Edit' onclick=\"window.location.href='update?login=%s'\"/></form></td>", user.getLogin()));
            writer.append(String.format("<td><form><input type='button' value='Delete' onclick=\"window.location.href='delete?login=%s'\"/></form></td>", user.getLogin()));
        }
        writer.append("</table>");
        writer.flush();
    }
}
