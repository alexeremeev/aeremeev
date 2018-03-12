package ru.job4j.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.domain.User;
import ru.job4j.service.UserService;

import java.io.IOException;

@RestController
public class UserRegisterController {

    private UserService service;

    @Autowired
    public UserRegisterController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/account", produces = "application/json;charset=UTF-8")
    public String registerNewUser(@RequestParam String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(json, User.class);
        boolean result = this.service.registerNewUser(user.getUsername());
        ObjectNode node = mapper.getNodeFactory().objectNode();
        if (result) {
            User registered = this.service.findByUsername(user.getUsername());
            node.put("success", true);
            node.put("description", String.format("Account for %s opened, your password: %s",
                    registered.getUsername(), registered.getPassword()));
        } else {
            node.put("success", false);
            node.put("description", String.format("Account with username %s already exists",
                    user.getUsername()));
        }
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
    }

}
