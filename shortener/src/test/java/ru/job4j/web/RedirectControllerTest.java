package ru.job4j.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.domain.Url;
import ru.job4j.repository.UrlRepository;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RedirectController.class)
public class RedirectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlRepository repository;

    @Before
    public void setup() {
        Url mockUrl = new Url();
        mockUrl.setOriginalUrl("http://www.ya.ru");
        mockUrl.setShortUrl("abc123");
        given(repository.findByShortUrl(mockUrl.getShortUrl())).willReturn(mockUrl);
        given(repository.save(mockUrl)).willReturn(mockUrl);
    }

    @Test
    public void whenUseShortUrlThenRedirectToOriginalUrl() throws Exception {
        mockMvc.perform(
                get("/abc123")
                .contentType(MediaType.TEXT_HTML)
        )
                .andExpect(
                        status().is3xxRedirection()
                )
                .andExpect(redirectedUrl("http://www.ya.ru"));
    }



}