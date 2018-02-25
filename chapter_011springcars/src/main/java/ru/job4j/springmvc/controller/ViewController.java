package ru.job4j.springmvc.controller;

import com.google.gson.JsonObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.springmvc.models.Order;
import ru.job4j.springmvc.repo.OrderRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ViewController {

    private ApplicationContext context = new ClassPathXmlApplicationContext("spring-data-config.xml");
    private OrderRepository repository = context.getBean(OrderRepository.class);

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
        jsonOrders.addProperty("orders", ListToJson.listToJsonArray((List<Order>) repository.findAll()).toString());
        return jsonOrders.toString();
    }

}
