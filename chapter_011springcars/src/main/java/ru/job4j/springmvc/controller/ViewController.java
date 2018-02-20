package ru.job4j.springmvc.controller;

import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.springmvc.dao.GenericDAO;
import ru.job4j.springmvc.models.Order;

import javax.servlet.http.HttpSession;

@RestController
public class ViewController {

    private GenericDAO dao = new GenericDAO();

    @GetMapping(value = "/view",  produces = "application/json;charset=UTF-8")
    public String viewAllOrders(HttpSession session) {
        JsonObject jsonOrders = new JsonObject();
        int userId = 0;
        if (session != null) {
            if ((boolean) session.getAttribute("success")) {
                userId = (int) session.getAttribute("user_id");
            }
        }
        jsonOrders.addProperty("currentUser", userId);
        jsonOrders.addProperty("orders", ListToJson.listToJsonArray(dao.getAll(Order.class)).toString());
        return jsonOrders.toString();
    }

}
