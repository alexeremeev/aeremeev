package ru.job4j.web;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.domain.*;
import ru.job4j.repository.OrderRepository;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(FilterOrderController.class)
public class FilterOrderControllerTest {

    private JsonArray ordersArray;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository repository;

    @Before
    public void setup() {
        Car mockCar = new Car();
        mockCar.setId(1);
        mockCar.setName("Mock Car");

        User mockUser = new User();
        mockUser.setLogin("Mock User");

        Order mockOrder = new Order();
        mockOrder.setId(1);
        mockOrder.setPrice(1);
        mockOrder.setMileage(1);
        mockOrder.setReleaseDate(new Timestamp(0L));
        mockOrder.setOrderDate(new Timestamp(0L));
        mockOrder.setCar(mockCar);
        mockOrder.setUser(mockUser);
        List<Order> orders = Collections.singletonList(mockOrder);

        given(repository.findByImagesSizeAndCarModelNameAndOrderDateGreaterThan(
                -1, "%audi%", new Timestamp(0L))).willReturn(orders);

        this.ordersArray = ListToJson.listToJsonArray(orders);
    }

    @Test
    @WithMockUser(value = "Mock User")
    public void whenRequestAllOrdersByAuthorizedThenGetOrdersList() throws Exception {
        JsonObject expected = new JsonObject();
        expected.addProperty("currentUser", "Mock User");
        expected.addProperty("orders", this.ordersArray.toString());
        mockMvc.perform(
                get("/filter")
                        .param("isEmpty", "-1")
                        .param("model", "audi")
                        .param("orderDate", "0")
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(
                        status().isOk())
                .andExpect(
                        content().string(expected.toString()))
                .andDo(
                        print());
    }
}