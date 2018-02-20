package ru.job4j.springmvc.controller;

import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.*;
import ru.job4j.springmvc.dao.UserDAO;
import ru.job4j.springmvc.models.User;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @GetMapping(value = "/login", produces = "application/json")
    public String sessionStatus(HttpSession session) {
        boolean success = (boolean) session.getAttribute("success");
        JsonObject json = new JsonObject();
        json.addProperty("success", success);
        return json.toString();
    }

    @PostMapping(value = "/login", produces = "application/json")
    public String validateSession(@RequestParam String login,
                                  @RequestParam String password,
                                  @RequestParam String register,
                                  HttpSession session) {
        boolean isRegister = Boolean.valueOf(register);
        session.setAttribute("currentOrder", 0);
        session.setAttribute("success", false);

        JsonObject object = new JsonObject();
        object.addProperty("success", false);
        UserDAO dao = new UserDAO();
        User user;

        if (isRegister) {
            user = new User();
            user.setLogin(login);
            user.setPassword(password);
            dao.saveOrUpdate(user);
            if (user.getId() > 0) {
                session.setAttribute("success", true);
                object.addProperty("success", true);
            }
        } else {
            user = dao.isCredential(login, password);
            if (user != null) {
                session.setAttribute("success", true);
                object.addProperty("success", true);
            }
            session.setAttribute("user_id", user.getId());
        }
        return object.toString();
    }
}
