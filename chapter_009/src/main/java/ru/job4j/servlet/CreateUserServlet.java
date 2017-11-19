package ru.job4j.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * class CreateUserServlet - Servlet создания нового пользователя.
 */
public class CreateUserServlet extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter writer = new PrintWriter(resp.getOutputStream());
//        writer.append("<!DOCTYPE html><html><head><meta charset='windows-1251'>");
//        writer.append("<title>User Storage</title></head><body><h1>Add New User</h1>");
//        writer.append("<form action='create' method='post'><table>");
//        writer.append("<tr><td>Name:</td><td><input type='name' name='name'/></td></tr>");
//        writer.append("<tr><td>Login:</td><td><input type='login' name='login'/></td></tr>");
//        writer.append("<tr><td>Email:</td><td><input type='email' name='email'/></td></tr>");
//        writer.append("<tr><td><input type='submit' value='Save User'/></td></tr></table>");
//        writer.append("</form><br/><a href='get'>View All Users</a></body></html>");
//        writer.flush();
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User(name, login, email, System.currentTimeMillis());
        if (UserStorage.getInstance().addUser(user)) {
//            writer.append("User successfully added!");
//            writer.flush();
            resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
        } else {
            writer.append("Login already exists! Try again!");
            writer.append("<br/><a href='create'>Create New User</a>");
            writer.append("<br/><a href='get'>View All Users</a>");
            writer.flush();
        }
    }
}
