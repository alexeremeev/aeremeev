package ru.job4j.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.domain.*;
import ru.job4j.repository.CarRepository;
import ru.job4j.repository.OrderRepository;
import ru.job4j.repository.UserRepository;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CreateController.class)
public class CreateControllerTest {

    private String login = "Mock User";

    private String car;

    private String order;

    private String release = "2018-03-09";

    private Order mockOrder = new Order();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setup() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Body mockBody = new Body();
        mockBody.setId(1);
        Engine mockEngine = new Engine();
        mockEngine.setId(1);
        Gearbox mockGearbox = new Gearbox();
        mockGearbox.setId(1);
        Model mockModel = new Model();
        mockModel.setId(1);
        Transmission mockTrans = new Transmission();
        mockTrans.setId(1);
        Car mockCar = new Car();
        mockCar.setId(1);
        mockCar.setName("Mock Car");
        mockCar.setBody(mockBody);
        mockCar.setEngine(mockEngine);
        mockCar.setModel(mockModel);
        mockCar.setGearbox(mockGearbox);
        mockCar.setTransmission(mockTrans);

        this.car = mapper.writeValueAsString(mockCar);

        User mockUser = new User();
        mockUser.setLogin(login);

        mockOrder.setId(1);
        mockOrder.setPrice(1);
        mockOrder.setMileage(1);

        this.order = mapper.writeValueAsString(mockOrder);

        given(this.carRepository.save(mockCar)).willReturn(mockCar);
        given(this.userRepository.findByLogin(login)).willReturn(mockUser);
        given(this.orderRepository.save(mockOrder)).willReturn(mockOrder);

    }

    @Test
    @WithAnonymousUser
    public void whenCreateNewOrderWithoutParamsThenGet401Error() throws Exception {
        mockMvc.perform(
                post("/create").contentType(MediaType.TEXT_HTML))
                .andExpect(
                        status().is4xxClientError())
                .andDo(
                        print());

    }
    @Test
    @WithMockUser(value = "Mock User")
    public void whenCreateNewOrderThenGetOrderId() throws Exception {
        mockMvc.perform(
                post("/create")
                        .sessionAttr("currentOrder", 0)
                        .param("release", this.release)
                        .param("car", this.car)
                        .param("carId", "1")
                        .param("order", this.order)
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(
                        status().isOk())
                .andExpect(
                        content().string(mockOrder.getId().toString())
                )
                .andDo(
                        print());
    }

}