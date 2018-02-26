package ru.job4j.springmvc.controller;

import com.google.gson.JsonObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public String viewAllOrders() {
        JsonObject jsonOrders = new JsonObject();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String login = "-=StubLogin-===AeX7y";

        if (authentication != null) {
            login = ((UserDetails) authentication.getPrincipal()).getUsername();
        }

        jsonOrders.addProperty("currentUser", login);
        jsonOrders.addProperty("orders", ListToJson.listToJsonArray((List<Order>) repository.findAll()).toString());
        return jsonOrders.toString();
    }

}
