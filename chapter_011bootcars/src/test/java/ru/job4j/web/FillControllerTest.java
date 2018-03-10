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
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.domain.*;
import ru.job4j.repository.BodyRepository;
import ru.job4j.repository.OrderRepository;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FillController.class)
public class FillControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BodyRepository bodyRepository;

    @MockBean
    private Repositories repositories;

    @Before
    public void setup() {
        Body mockBody = new Body();
        mockBody.setId(1);
        mockBody.setName("Mock Body");
        given(repositories.getRepositoryFor(Body.class)).willReturn(Optional.of(bodyRepository));
        given(bodyRepository.findAll()).willReturn(Collections.singletonList(mockBody));
    }

    @Test
    @WithAnonymousUser
    public void whenTryAccessCarPartsThenGet401Error() throws Exception {
        mockMvc.perform(
                get("/fill").contentType(MediaType.TEXT_HTML))
                .andExpect(
                        status().is4xxClientError())
                .andDo(
                        print());

    }

    @Test
    @WithMockUser(value = "Mock User")
    public void whenAuthorizedThenGetBodyParts() throws Exception {
        mockMvc.perform(
                get("/fill")
                        .param("type", "Body")
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(
                        status().isOk())
                .andDo(
                        print());

    }
}