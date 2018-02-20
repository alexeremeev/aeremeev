package ru.job4j.springmvc.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.springmvc.dao.GenericDAO;
import ru.job4j.springmvc.models.Car;
import ru.job4j.springmvc.models.Order;
import ru.job4j.springmvc.models.User;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class CreateController {

    private GenericDAO dao = new GenericDAO();

    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public String addNewOrder(@RequestParam("release") String releaseDate,
                              @RequestParam("car") String carJson,
                              @RequestParam("carId") String carIdParam,
                              @RequestParam("order") String orderJson,
                              HttpSession session) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        int userId = (int) session.getAttribute("user_id");
        int orderId = (int) session.getAttribute("currentOrder");
        if (userId != 0) {
            Timestamp release = new Timestamp(System.currentTimeMillis());
            try {
                release = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(releaseDate).getTime());
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
            Car car = mapper.readValue(carJson, Car.class);
            int carId = Integer.valueOf(carIdParam);
            if (carId != -1) {
                car.setId(carId);
            }
            new GenericDAO<>().saveOrUpdate(car);

            User user = new User();
            user.setId(userId);

            Order order = mapper.readValue(orderJson, Order.class);
            order.setCar(car);
            order.setReleaseDate(release);
            order.setUser(user);
            order.setOrderDate(new Timestamp(System.currentTimeMillis()));
            if (orderId > 0) {
                order.setId(orderId);
            }
            dao.saveOrUpdate(order);
            orderId = order.getId();
            session.setAttribute("currentOrder", orderId);
        }
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(JsonNodeFactory.instance.numberNode(orderId));
    }

}
