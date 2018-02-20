package ru.job4j.springmvc.controller;

import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.springmvc.dao.OrderDAO;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
public class FilterOrderController {
    @GetMapping(value = "/filter", produces = "application/json;charset=UTF-8")
    public String doGet(@RequestParam String isEmpty,
                        @RequestParam String model,
                        @RequestParam String orderDate,
                        HttpSession session) throws IOException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("isEmpty", Integer.valueOf(isEmpty));
        parameters.put("model", "%" + model + "%");
        parameters.put("orderDate", new Timestamp(Long.valueOf(orderDate)));

        JsonObject json = new JsonObject();
        Integer userId = -1;
        if (session != null) {
            boolean success = (boolean) session.getAttribute("success");
            if (success) {
                userId = (int) session.getAttribute("user_id");
            }
        }
        json.addProperty("currentUser", userId);
        String hql = "FROM Order as o WHERE o.images.size != :isEmpty and o.car.model.name like :model and o.orderDate > :orderDate";
        json.addProperty("orders", ListToJson.listToJsonArray(new OrderDAO().getOrdersByFilter(hql, parameters)).toString());
        return json.toString();
    }
}
