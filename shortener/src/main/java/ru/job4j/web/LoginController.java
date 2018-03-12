package ru.job4j.web;

import com.google.gson.JsonObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        json.addProperty("username", name);
        return json.toString();
    }
}
