package ru.job4j.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import ru.job4j.dao.GenericDAO;
import ru.job4j.models.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Edit existing order servlet.
 * @author aeremeev.
 * @version 1
 * @since 05.02.2018
 */
public class Edit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.getNodeFactory().objectNode();
        HttpSession session = req.getSession(false);
        int orderId = (int) session.getAttribute("currentOrder");
        boolean success = (boolean) session.getAttribute("success");
        int userId = -1;
        if (orderId != -1) {
            if (success) {
                userId = (int) session.getAttribute("user_id");
            }
            node.put("orderProperties", orderToNode(new GenericDAO<Order>().findById(Order.class, orderId)));
        }
        node.put("currentUser", userId);
        node.put("order", orderId);
        writer.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node));
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.valueOf(req.getParameter("order"));
        HttpSession session = req.getSession(false);
        session.setAttribute("currentOrder", orderId);
    }

    public ArrayNode orderToNode(Order order) {
        ArrayNode result = JsonNodeFactory.instance.arrayNode();
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("orderId", order.getId());
        node.put("mileage", order.getMileage());
        node.put("price", order.getPrice());
        node.put("sold", order.isSold());
        node.put("carName", order.getCar().getName());
        node.put("carId", order.getCar().getId());
        node.put("modelId", order.getCar().getModel().getId());
        node.put("bodyId", order.getCar().getBody().getId());
        node.put("transmissionId", order.getCar().getTransmission().getId());
        node.put("engineId", order.getCar().getEngine().getId());
        node.put("gearboxId", order.getCar().getGearbox().getId());
        node.put("release", String.format("%1$TF", order.getReleaseDate()));
        node.put("userId", order.getUser().getId());
        result.add(node);
        return result;
    }
}
