package ru.job4j.web;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.domain.Order;
import ru.job4j.repository.OrderRepository;


import java.util.List;

@RestController
public class ViewController {

    @Autowired
    private OrderRepository repository;

    @GetMapping(value = "/view",  produces = "application/json;charset=UTF-8")
    public String viewAllOrders() {
        JsonObject jsonOrders = new JsonObject();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String login = "-=StubLogin-===AeX7y";

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            login = ((UserDetails) authentication.getPrincipal()).getUsername();
        }

        jsonOrders.addProperty("currentUser", login);
        jsonOrders.addProperty("orders", ListToJson.listToJsonArray((List<Order>) repository.findAll()).toString());
        return jsonOrders.toString();
    }

}
