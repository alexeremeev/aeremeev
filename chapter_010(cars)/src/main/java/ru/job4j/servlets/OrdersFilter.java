package ru.job4j.servlets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import ru.job4j.dao.OrderDAO;
import ru.job4j.models.Order;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersFilter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("isEmpty", Integer.valueOf(req.getParameter("isEmpty")));
        parameters.put("model", req.getParameter("model"));
        parameters.put("orderDate", new Timestamp(Long.valueOf(req.getParameter("orderDate"))));

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.getNodeFactory().objectNode();

        HttpSession session = req.getSession(false);
        Integer userId = -1;
        if (session != null) {
            boolean success = (boolean) session.getAttribute("success");
            if (success) {
                userId = (int) session.getAttribute("user_id");
            }
        }
        rootNode.put("currentUser", userId);
        String hql = "FROM Order as o WHERE o.images.size != :isEmpty and o.car.model.name like :model and o.orderDate > :orderDate";
        rootNode.put("orders", listToJsonArray(new OrderDAO().getOrdersByFilter(hql, parameters)).toString());
        writer.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode));
        writer.flush();
    }

    public ArrayNode listToJsonArray(List<Order> orders) {
        ArrayNode array = JsonNodeFactory.instance.arrayNode();
        for (Order order: orders) {
            ObjectNode object = JsonNodeFactory.instance.objectNode();
            object.put("orderId", order.getId());
            object.put("mileage", order.getMileage());
            object.put("sold", order.isSold());
            object.put("price", order.getPrice());
            object.put("carName", order.getCar().getName());
            object.put("carId", order.getCar().getId());
            object.put("date", String.format("%1$TY", order.getReleaseDate()));
            object.put("userId", order.getUser().getId());
            array.add(object);
        }
        return array;
    }
}
