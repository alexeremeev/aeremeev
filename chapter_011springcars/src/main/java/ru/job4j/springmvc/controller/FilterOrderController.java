package ru.job4j.springmvc.controller;

import com.google.gson.JsonObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.springmvc.models.Order;
import ru.job4j.springmvc.repo.OrderRepository;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class FilterOrderController {

    private ApplicationContext context = new ClassPathXmlApplicationContext("spring-data-config.xml");
    private OrderRepository repository = context.getBean(OrderRepository.class);

    @GetMapping(value = "/filter", produces = "application/json;charset=UTF-8")
    public String doGet(@RequestParam String isEmpty,
                        @RequestParam String model,
                        @RequestParam String orderDate,
                        HttpSession session) throws IOException {
        JsonObject json = new JsonObject();
        Integer userId = -1;
        if (session != null) {
            boolean success = (boolean) session.getAttribute("success");
            if (success) {
                userId = (int) session.getAttribute("user_id");
            }
    }
        json.addProperty("currentUser", userId);
        List<Order> requestedOrders = repository.findByImagesSizeAndCarModelNameAndOrderDateGreaterThan(
                Integer.valueOf(isEmpty), "%" + model + "%", new Timestamp(Long.valueOf(orderDate)));
        json.addProperty("orders", ListToJson.listToJsonArray(requestedOrders).toString());
        return json.toString();
    }
}
