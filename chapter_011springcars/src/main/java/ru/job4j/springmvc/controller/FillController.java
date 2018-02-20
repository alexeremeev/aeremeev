package ru.job4j.springmvc.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.springmvc.dao.GenericDAO;

@RestController
public class FillController {

    @GetMapping(value = "/fill", produces = "application/json;charset=UTF-8")
    public String fillParts(@RequestParam String type) {
        return listToJsonMap(type);
    }

    public String listToJsonMap(String type) {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                    new GenericDAO().getAll(Class.forName(String.format("ru.job4j.springmvc.models.%s", type))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
