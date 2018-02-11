package ru.job4j.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import ru.job4j.dao.GenericDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Fill servlet. Fills create form with car parts.
 * @author aeremeev.
 * @version 1
 * @since 05.02.2018
 */
public class Fill extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeReq = req.getParameter("type");
        PrintWriter out = resp.getWriter();
        out.append(listToJsonMap(typeReq));
        out.flush();
    }

    public String listToJsonMap(String type) {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                    new GenericDAO().getAll(Class.forName(String.format("ru.job4j.models.%s", type))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
