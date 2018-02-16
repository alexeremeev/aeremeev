package ru.job4j.springioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.job4j.springioc.model.User;
import ru.job4j.springioc.storage.Storage;

import java.util.List;


/**
 * Import user.
 * @author aeremeev.
 * @version 1
 * @since 16.02.2018
 */
@Component
public class ImportUser {

    private final Storage storage;

    @Autowired
    public ImportUser(Storage storage) {
        this.storage = storage;
    }

    public void add(User user) {
        this.storage.add(user);
    }

    public List<User> getAll() {
        return this.storage.getAll();
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        ImportUser start = (ImportUser) context.getBean("import");
        start.add(new User("Alex"));
        start.add(new User("Alexander"));
        start.add(new User("Aleksandr"));
        List<User> users = start.getAll();
        for (User user: users) {
            System.out.println(user.toString());
        }
    }
}
