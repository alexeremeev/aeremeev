package ru.job4j.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Url;
import ru.job4j.domain.UrlDTO;
import ru.job4j.repository.UrlRepository;
import ru.job4j.repository.UserRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class UrlShortenerController {

    private UrlRepository urlRepository;
    private UserRepository userRepository;

    @Autowired
    public UrlShortenerController(UrlRepository urlRepository, UserRepository userRepository) {
        this.urlRepository = urlRepository;
        this.userRepository = userRepository;
    }

    @PreAuthorize("#username == authentication.name")
    @GetMapping(value = "statistic/{username}", produces = "application/json;charset=UTF-8")
    public String getAllUserUrls(@PathVariable("username") String username) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode result = mapper.getNodeFactory().arrayNode();
        List<Url> urls = this.urlRepository.findByUserUsername(username);
        for (Url url: urls) {
            ObjectNode node = mapper.getNodeFactory().objectNode();
            node.put("original", url.getOriginalUrl());
            node.put("shortened", String.format("http://localhost:8080/shortener/%s", url.getShortUrl()));
            node.put("counter", url.getCounter());
            result.add(node);
        }
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
    }

    @PostMapping(value = "/register", produces = "application/json;charset=UTF-8")
    public String addNewUrl(@RequestParam String request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        UrlDTO dto = mapper.readValue(request, UrlDTO.class);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        String originalUrl = this.fillPrefix(dto.getUrl());
        ObjectNode node = mapper.getNodeFactory().objectNode();

        if (isValidUrl(originalUrl)) {
            final String shortUrl = Hashing.murmur3_32().hashString(originalUrl, StandardCharsets.UTF_8).toString();
            Url url = new Url();
            url.setOriginalUrl(originalUrl);
            url.setShortUrl(shortUrl);
            url.setUser(userRepository.findByUsername(username));
            url.setCounter(0);
            urlRepository.save(url);
            node.put("shortUrl", String.format("http://localhost:8080/shortener/%s", shortUrl));
        } else {
            node.put("shortUrl", "Please enter valid url!");
        }
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
    }

    private boolean isValidUrl(String url) {
        UrlValidator validator = new UrlValidator(new String[] {"http", "https"});
        return validator.isValid(url);
    }

    private String fillPrefix(String url) {
        if (!url.startsWith("http")) {
            url = String.format("http://%s", url);
        }
        return url;
    }
}
