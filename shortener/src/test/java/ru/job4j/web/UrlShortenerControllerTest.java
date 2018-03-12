package ru.job4j.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.hash.Hashing;
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
import ru.job4j.domain.Url;
import ru.job4j.domain.UrlDTO;
import ru.job4j.domain.User;
import ru.job4j.repository.UrlRepository;
import ru.job4j.repository.UserRepository;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UrlShortenerController.class)
public class UrlShortenerControllerTest {

    private String urlRegister;

    private Url mockUrl;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlRepository urlRepository;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setup() throws JsonProcessingException {
        User mockUser = new User();
        mockUser.setEnabled(true);
        mockUser.setUsername("MockUser");

        mockUrl = new Url();
        String originalUrl = "http://www.ya.ru";
        mockUrl.setOriginalUrl(originalUrl);
        String shortUrl = Hashing.murmur3_32().hashString(originalUrl, StandardCharsets.UTF_8).toString();
        mockUrl.setShortUrl(shortUrl);
        mockUrl.setUser(mockUser);

        given(this.urlRepository.findByUserUsername(mockUser.getUsername())).willReturn(Collections.singletonList(mockUrl));

        given(this.userRepository.findByUsername(mockUser.getUsername())).willReturn(mockUser);
        ObjectMapper mapper = new ObjectMapper();
        UrlDTO dto = new UrlDTO();
        dto.setUrl(originalUrl);
        dto.setRedirectType(301);
        this.urlRegister = mapper.writeValueAsString(dto);

    }

    @Test
    @WithMockUser("MockUser")
    public void whenGetUserUrlsThenReturnUrls() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode result = mapper.getNodeFactory().arrayNode();
        ObjectNode urlNode = mapper.getNodeFactory().objectNode();
        urlNode.put("original", mockUrl.getOriginalUrl());
        urlNode.put("shortened", String.format("http://localhost:8080/shortener/%s", mockUrl.getShortUrl()));
        urlNode.put("counter", mockUrl.getCounter());
        result.add(urlNode);

        mockMvc.perform(
                get("/statistic/MockUser")
                .contentType(MediaType.TEXT_HTML)
        )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        content().string(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result))
                );
    }

    @Test
    @WithMockUser("MockUser")
    public void whenAddNewUrlThenGetShortenedUrl() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode result = mapper.getNodeFactory().objectNode();
        result.put("shortUrl", String.format("http://localhost:8080/shortener/%s", this.mockUrl.getShortUrl()));

        mockMvc.perform(
                post("/register")
                .param("request", this.urlRegister)
                .contentType(MediaType.TEXT_HTML)
        )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        content().string(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result))
                );
    }

}