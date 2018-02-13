package ru.job4j.servlets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.JsonNodeFactory;
import ru.job4j.dao.GenericDAO;
import ru.job4j.models.Car;
import ru.job4j.models.Order;
import ru.job4j.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Create new order servlet.
 * @author aeremeev.
 * @version 1
 * @since 05.02.2018
 */
public class Create extends HttpServlet {

    private GenericDAO dao = new GenericDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        JsonObject object = new JsonObject();
        HttpSession session = req.getSession(false);
        int userId = 0;
        if (session != null) {
            if ((boolean) session.getAttribute("success")) {
                userId = (int) session.getAttribute("user_id");
            }
        }
        object.addProperty("currentUser", userId);
        object.addProperty("orders", listToJsonArray(dao.getAll(Order.class)).toString());
        System.out.println(object.toString());
        writer.append(object.toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        HttpSession session = req.getSession(false);
        int userId = (int) session.getAttribute("user_id");
        int orderId = (int) session.getAttribute("currentOrder");
        if (userId != 0) {
            Timestamp release = new Timestamp(System.currentTimeMillis());
            try {
                release = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("release")).getTime());
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
            Car car = mapper.readValue(req.getParameter("car"), Car.class);
            int carId = Integer.valueOf(req.getParameter("carId"));
            if (carId != -1) {
                car.setId(carId);
            }
            new GenericDAO<>().saveOrUpdate(car);

            User user = new User();
            user.setId(userId);

            Order order = mapper.readValue(req.getParameter("order"), Order.class);
            order.setCar(car);
            order.setReleaseDate(release);
            order.setUser(user);
            if (orderId != -1) {
                order.setId(orderId);
            }
            dao.saveOrUpdate(order);
            orderId = order.getId();
            session.setAttribute("currentOrder", orderId);
        }
        writer.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(JsonNodeFactory.instance.numberNode(orderId)));
        writer.flush();
    }

    public JsonArray listToJsonArray(List<Order> orders) {
        JsonArray array = new JsonArray();
        for (Order order: orders) {
            JsonObject object = new JsonObject();
            object.addProperty("orderId", order.getId());
            object.addProperty("mileage", order.getMileage());
            object.addProperty("sold", order.isSold());
            object.addProperty("price", order.getPrice());
            object.addProperty("carName", order.getCar().getName());
            object.addProperty("carId", order.getCar().getId());
            object.addProperty("date", String.format("%1$TY", order.getReleaseDate()));
            object.addProperty("userId", order.getUser().getId());
            array.add(object);
        }
        return array;
    }
}
