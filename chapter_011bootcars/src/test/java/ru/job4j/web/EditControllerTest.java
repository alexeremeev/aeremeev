package ru.job4j.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import ru.job4j.repository.OrderRepository;

import java.sql.Timestamp;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EditController.class)
public class EditControllerTest {

    private String login = "Mock User";

    private String expected;

    private Order mockOrder = new Order();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository repository;

    @Before
    public void setup() throws JsonProcessingException {
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

        User mockUser = new User();
        mockUser.setLogin(login);

        mockOrder.setId(1);
        mockOrder.setPrice(1);
        mockOrder.setMileage(1);
        mockOrder.setReleaseDate(new Timestamp(0L));

        mockOrder.setUser(mockUser);
        mockOrder.setCar(mockCar);

        given(repository.findById(mockOrder.getId())).willReturn(Optional.of(mockOrder));

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.getNodeFactory().objectNode();
        ArrayNode orders = new EditController().orderToNode(mockOrder);
        node.put("orderProperties", orders);
        node.put("currentUser", this.login);
        node.put("order" , mockOrder.getId());

        this.expected = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);


    }

    @Test
    @WithAnonymousUser
    public void whenTryToGetUnauthorizedAccessToEditOrderThenGet401Error() throws Exception {
        mockMvc.perform(
                get("/edit").contentType(MediaType.TEXT_HTML))
                .andExpect(
                        status().is4xxClientError())
                .andDo(
                        print());
    }

    @Test
    @WithMockUser(value = "Mock User")
    public void whenGetOrderToEditThenGetOrderInJson() throws Exception {

        mockMvc.perform(
                get("/edit")
                .sessionAttr("currentOrder", 1)
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(
                        status().isOk())
                .andExpect(
                        content().string(this.expected)
                )
                .andDo(
                        print());

    }
}