package ru.job4j.springmvc.controller;

import com.google.gson.JsonObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.springmvc.models.User;
import ru.job4j.springmvc.repo.UserRepository;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    private final static String ANONYMOUS = "anonymousUser";

    @GetMapping(value = "/login", produces = "application/json")
    public String sessionStatus() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        boolean success = false;
        String name = authentication.getName();
        if (!ANONYMOUS.equals(name)) {
            success = true;
        }
        JsonObject json = new JsonObject();
        json.addProperty("success", success);
        return json.toString();
    }
}
