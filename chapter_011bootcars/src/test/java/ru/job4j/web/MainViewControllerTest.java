package ru.job4j.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(MainViewController.class)
public class MainViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    public void whenOpenRootTheGoToIndexView() throws Exception {
        mockMvc.perform(
                get("/")
                .contentType(MediaType.TEXT_HTML)
        )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        view().name("index")
                );
    }

    @Test
    @WithAnonymousUser
    public void whenOpenCreateAsAnonymousThenGet401Error() throws Exception {
        mockMvc.perform(
                get("/create")
                .contentType(MediaType.TEXT_HTML)
        )
                .andExpect(
                        status().is4xxClientError()
                );
    }

    @Test
    @WithMockUser(value = "Mock User")
    public void whenOpenCreateAsAuthorizedThenGetView() throws Exception{
        mockMvc.perform(
                get("/create")
                        .contentType(MediaType.TEXT_HTML)
        )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        view().name("create")
                );
    }

}