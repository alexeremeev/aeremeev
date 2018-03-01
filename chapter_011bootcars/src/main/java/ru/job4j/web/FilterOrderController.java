package ru.job4j.web;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.domain.Order;
import ru.job4j.repository.OrderRepository;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class FilterOrderController {

    @Autowired
    private OrderRepository repository;

    @GetMapping(value = "/filter", produces = "application/json;charset=UTF-8")
    public String doGet(@RequestParam String isEmpty,
                        @RequestParam String model,
                        @RequestParam String orderDate) throws IOException {
        JsonObject json = new JsonObject();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String login = authentication.getName();
        json.addProperty("currentUser", login);
        List<Order> requestedOrders = repository.findByImagesSizeAndCarModelNameAndOrderDateGreaterThan(
                Integer.valueOf(isEmpty), "%" + model + "%", new Timestamp(Long.valueOf(orderDate)));
        json.addProperty("orders", ListToJson.listToJsonArray(requestedOrders).toString());
        return json.toString();
    }
}
