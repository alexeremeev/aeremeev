package ru.job4j.springmvc.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.springmvc.models.Car;
import ru.job4j.springmvc.models.Order;
import ru.job4j.springmvc.models.User;
import ru.job4j.springmvc.repo.CarRepository;
import ru.job4j.springmvc.repo.OrderRepository;
import ru.job4j.springmvc.repo.UserRepository;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class CreateController {

    private ApplicationContext context = new ClassPathXmlApplicationContext("spring-data-config.xml");
    private CarRepository carRepo = context.getBean(CarRepository.class);
    private OrderRepository orderRepo = context.getBean(OrderRepository.class);
    private UserRepository userRepo = context.getBean(UserRepository.class);

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
