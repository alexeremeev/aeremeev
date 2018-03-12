package ru.job4j.web;

import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenViewAsAnonymousThenSuccessPropertyIsFalse() throws Exception {
        JsonObject expected = new JsonObject();
        expected.addProperty("success", false);
        expected.addProperty("username", "anonymousUser");
        mockMvc.perform(
                get("/login")
                        .contentType(MediaType.TEXT_HTML)
        )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        content().string(expected.toString())
                )
                .andDo(print());
    }

    @Test
    @WithMockUser(value = "Mock User")
    public void whenViewAsAuthorizedThenSuccessPropertyIsTrue() throws Exception {
        JsonObject expected = new JsonObject();
        expected.addProperty("success", true);
        expected.addProperty("username", "Mock User");
        mockMvc.perform(
                get("/login")
                        .contentType(MediaType.TEXT_HTML)
        )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        content().string(expected.toString())
                )
                .andDo(print());
    }
}