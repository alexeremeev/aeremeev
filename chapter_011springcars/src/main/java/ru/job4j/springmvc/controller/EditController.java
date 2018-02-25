package ru.job4j.springmvc.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.springmvc.models.Order;
import ru.job4j.springmvc.repo.OrderRepository;

import javax.servlet.http.HttpSession;

import java.io.IOException;

@RestController
public class EditController {

    private ApplicationContext context = new ClassPathXmlApplicationContext("spring-data-config.xml");
    private OrderRepository orderRepo = context.getBean(OrderRepository.class);

    @GetMapping(value = "/edit", produces = "application/json;charset=UTF-8")
    public String getExistingOrder(HttpSession session) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.getNodeFactory().objectNode();
        int orderId = (int) session.getAttribute("currentOrder");
        boolean success = (boolean) session.getAttribute("success");
        int userId = -1;
        if (orderId != -1) {
            if (success) {
                userId = (int) session.getAttribute("user_id");
            }
            node.put("orderProperties", orderToNode(orderRepo.findById(orderId).get()));
        }
        node.put("currentUser", userId);
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
        node.put("userId", order.getUser().getId());
        result.add(node);
        return result;
    }
}
