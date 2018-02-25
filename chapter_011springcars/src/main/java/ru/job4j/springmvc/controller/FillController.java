package ru.job4j.springmvc.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FillController {

    private ApplicationContext context = new ClassPathXmlApplicationContext("spring-data-config.xml");
    private Repositories repositories = new Repositories(context);

    @GetMapping(value = "/fill", produces = "application/json;charset=UTF-8")
    public String fillParts(@RequestParam String type) {
        return listToJsonMap(type);
    }

    public String listToJsonMap(String type) {
        ObjectMapper mapper = new ObjectMapper();

        String result = null;
        try {
            CrudRepository repository = (CrudRepository) repositories.
                    getRepositoryFor(Class.forName(String.format("ru.job4j.springmvc.models.%s", type))).get();
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(repository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
