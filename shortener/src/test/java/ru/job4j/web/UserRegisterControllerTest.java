package ru.job4j.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.domain.User;
import ru.job4j.service.UserService;

import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserRegisterController.class)
public class UserRegisterControllerTest {

    private String user;

    private String registered;

    private User mockUser;

    private User registeredUser;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Before
    public void setup() throws IOException {
        mockUser = new User();
        mockUser.setId(1);
        mockUser.setUsername("user");
        mockUser.setPassword("pass");
        mockUser.setEnabled(true);
        ObjectMapper mapper = new ObjectMapper();
        this.user = mapper.writeValueAsString(mockUser);
        given(service.registerNewUser(mockUser.getUsername())).willReturn(true);
        given(service.findByUsername(mockUser.getUsername())).willReturn(mockUser);

        registeredUser = new User();
        registeredUser.setId(2);
        registeredUser.setEnabled(true);
        registeredUser.setUsername("Registered");
        registeredUser.setPassword("pass");
        this.registered = mapper.writeValueAsString(registeredUser);
        given(service.registerNewUser(registeredUser.getUsername())).willReturn(false);
    }

    @Test
    public void whenRegisterNewUserThenSuccessIsTrue() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode result = mapper.getNodeFactory().objectNode();
        result.put("success", true);
        result.put("description", String.format("Account for %s opened, your password: %s",
                mockUser.getUsername(), mockUser.getPassword()));

        mockMvc.perform(
                post("/account")
                .param("json", this.user)
        )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        content().string(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result))
                );
    }

    @Test
    public void whenRegisterAlreadyExistingUserThenSuccessIsFalse() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode result = mapper.getNodeFactory().objectNode();
        result.put("success", false);
        result.put("description", String.format("Account with username %s already exists",
                registeredUser.getUsername()));

        mockMvc.perform(
                post("/account")
                        .param("json", this.registered)
        )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        content().string(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result))
                );

    }

}