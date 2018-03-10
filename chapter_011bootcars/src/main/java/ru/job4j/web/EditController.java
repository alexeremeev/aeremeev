package ru.job4j.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.domain.Order;
import ru.job4j.repository.OrderRepository;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class EditController {

    @Autowired
    private OrderRepository orderRepo;

    @GetMapping(value = "/edit", produces = "application/json;charset=UTF-8")
    public String getExistingOrder(HttpSession session) throws IOException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.getNodeFactory().objectNode();
        int orderId = (int) session.getAttribute("currentOrder");
        String login = "-=StubLogin-===AeX7y";
        if (authentication != null) {
            login = ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        if (orderId != -1) {
            node.put("orderProperties", orderToNode(orderRepo.findById(orderId).get()));
        }
        node.put("currentUser", login);
        node.put("order", orderId);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
    }

    @PostMapping(value = "/edit")
    public void setCurrentOrder(@RequestParam String order, HttpSession session) {
        int orderId = Integer.valueOf(order);
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
        node.put("userName", order.getUser().getLogin());
        result.add(node);
        return result;
    }
}
