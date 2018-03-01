package ru.job4j.web;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.configuration.ApplicationContextProvider;

@RestController
public class FillController {

    private Repositories repositories = new Repositories(ApplicationContextProvider.getContext());

    @GetMapping(value = "/fill", produces = "application/json;charset=UTF-8")
    public String fillParts(@RequestParam String type) {
        return listToJsonMap(type);
    }

    public String listToJsonMap(String type) {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            CrudRepository repository = (CrudRepository) repositories.
                    getRepositoryFor(Class.forName(String.format("ru.job4j.domain.%s", type))).get();
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(repository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
