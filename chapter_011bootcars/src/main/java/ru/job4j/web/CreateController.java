package ru.job4j.web;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.domain.Car;
import ru.job4j.domain.Order;
import ru.job4j.domain.User;
import ru.job4j.repository.CarRepository;
import ru.job4j.repository.OrderRepository;
import ru.job4j.repository.UserRepository;


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class CreateController {

    @Autowired
    private CarRepository carRepo;
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private UserRepository userRepo;

    @PostMapping(value = "/create", produces = "application/json;charset=UTF-8")
    public String addNewOrder(@RequestParam("release") String releaseDate,
                              @RequestParam("car") String carJson,
                              @RequestParam("carId") String carIdParam,
                              @RequestParam("order") String orderJson,
                              HttpSession session) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String login = ((UserDetails) authentication.getPrincipal()).getUsername();
        int orderId = (int) session.getAttribute("currentOrder");
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
        carRepo.save(car);

        User user = userRepo.findByLogin(login);

        Order order = mapper.readValue(orderJson, Order.class);
        order.setCar(car);
        order.setReleaseDate(release);
        order.setUser(user);
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        if (orderId > 0) {
            order.setId(orderId);
        }
        orderRepo.save(order);
        orderId = order.getId();
        session.setAttribute("currentOrder", orderId);

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(JsonNodeFactory.instance.numberNode(orderId));
    }

}
