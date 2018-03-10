package ru.job4j.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonArray;
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
import ru.job4j.repository.ImageRepository;
import ru.job4j.repository.OrderRepository;

import javax.xml.bind.DatatypeConverter;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ImageController.class)
public class ImageControllerTest {

    private JsonArray expected = new JsonArray();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageRepository repository;

    @Before
    public void setup() {
        Order order = new Order();
        order.setId(1);

        Image image = new Image();
        image.setData("Picture".getBytes());
        image.setId(1);
        image.setOrder(order);

        given(repository.findByOrderId(order.getId())).willReturn(Collections.singletonList(image));

        expected.add(String.format("data:image/jpeg;base64,%s", DatatypeConverter.printBase64Binary(image.getData())));
    }

    @Test
    @WithMockUser(value = "Mock User")
    public void whenAccessOrderImagesThenGetImageData() throws Exception {
        mockMvc.perform(
                get("/image")
                    .param("order", "1")
                    .contentType(MediaType.TEXT_HTML))
                .andExpect(
                    status().isOk()
                )
                .andExpect(
                        content().string(this.expected.toString())
                )
                .andDo(print());
    }

}